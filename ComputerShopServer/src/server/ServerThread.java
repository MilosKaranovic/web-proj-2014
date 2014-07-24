package server;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import model.CategoryBean;
import model.ComponentBean;
import model.DeviceBean;
import model.UploadedFileBean;
import model.UserBean;

import org.apache.commons.fileupload.FileItem;

import communication.CommandMessage;

public class ServerThread extends Thread {

	private Socket sock;
	private ArrayList<UserBean> users;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	/*static Logger logger = Logger.getLogger(ServerThread.class);
	PropertyConfigurator.configure(args[0]);
		logger.debug("this is a sample log message.");*/

	public ServerThread(Socket sock, ArrayList<UserBean> users) {
		
		System.out.println(new Date() + " : New client request!  ServerThread ID: " + this.getId());
		
		this.sock = sock;
		this.users = users;
		try {

			// inicijalizuj izlazni stream
			out = new ObjectOutputStream(sock.getOutputStream());

			// inicijalizuj ulazni stream
			in = new ObjectInputStream(sock.getInputStream());

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		start();
	}

	public void run() {
		try {
			// procitaj zahtev
			CommandMessage request = (CommandMessage) in.readObject();
			
			if (request.getCommand().equals("loginUser"))
				out.writeObject(loginResponse(request));
			if (request.getCommand().equals("logoutUser"))
				out.writeObject(logoutResponse(request));
			if (request.getCommand().equals("registerUser"))
				out.writeObject(registerResponse(request));
			
			if (request.getCommand().equals("currentCategories"))
				out.writeObject(currentCategoriesResponse(request));
			
			if (request.getCommand().equals("createCategory"))
				out.writeObject(createCategoryResponse(request));
			
			if (request.getCommand().equals("savePicture"))
				out.writeObject(savePictureResponse(request));
			
			if (request.getCommand().equals("createComponent"))
				out.writeObject(createComponentResponse(request));
			
			if (request.getCommand().equals("currentComponents"))
				out.writeObject(currentComponentsResponse(request));
			
			if (request.getCommand().equals("currentDevices"))
				out.writeObject(currentDevicesResponse(request));
			
			if (request.getCommand().equals("createDevice"))
				out.writeObject(createDeviceResponse(request));
/*			
			if (request.getCommand().equals("addAuthor"))
				out.writeObject(addAuthorResponse(request));
			if (request.getCommand().equals("ucitavanjeAutora"))
				out.writeObject(getAuthorsResponse(request));
			if (request.getCommand().equals("nadjiAutora"))
				out.writeObject(findAuthorsResponse(request));
			if (request.getCommand().equals("deleteAutor"))
				out.writeObject(deleteAutorResponse(request));
			if (request.getCommand().equals("editAutor"))
				out.writeObject(editAutorResponse(request));
			
			if (request.getCommand().equals("addSlike"))
				out.writeObject(addPictureResponse(request));
			if (request.getCommand().equals("ucitavanjeSlika"))
				out.writeObject(getPicturesResponse(request));
			if (request.getCommand().equals("deleteSlike"))
				out.writeObject(deletePictureResponse(request));
			if (request.getCommand().equals("editSlike"))
				out.writeObject(editPictureResponse(request));*/
			
			// zatvori streamove i konekciju
			in.close();
			out.close();
			sock.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public CommandMessage loginResponse(CommandMessage request) {
		
		CommandMessage retVal = new CommandMessage();
		retVal.setCommand("failed");
		UserBean caller = (UserBean) request.getObject();

		/*StringTokenizer st = new StringTokenizer((String) request.getObject());
		
		String username = st.nextToken();
		String password = st.nextToken();*/
		
		if (DBMS.getInstance().getUsersDatabase().isEmpty()) {
			retVal.setCommand("busy");
			return retVal;
		}
		
		for (UserBean user : DBMS.getInstance().getUsersDatabase()) {
			if (user.getUsername().equals(caller.getUsername())) {
				if (user.getPassword().equals(caller.getPassword())) {

					retVal.setCommand("success");
					user.setLoggedIn(true);
					caller.setId(user.getId());
					caller.setRole(user.getRole());
					DBMS.getInstance().getSessionUsers().add(user);
					// Ubaciti u logger  - TODO
					System.out.println("\t=> Ulogovao se korisnik - " + caller.getUsername());
					retVal.setObject((Object) caller);
					return retVal;
					
				}
				else {
					retVal.setCommand("wrong password");
					return retVal;
				}
			}
		}

		return retVal;
	}
	
	public CommandMessage logoutResponse(CommandMessage request) {
		StringTokenizer st = new StringTokenizer((String) request.getObject());
		CommandMessage retVal = new CommandMessage();
		retVal.setCommand("failed");

		String username = st.nextToken();
		
		if (DBMS.getInstance().getUsersDatabase().isEmpty()) {
			retVal.setCommand("busy");
			return retVal;
		}
		
		for (UserBean user : DBMS.getInstance().getUsersDatabase()) {
			if (user.getUsername().equals(username)) {
				for (UserBean sessionUser : DBMS.getInstance().getSessionUsers()) {
					if (sessionUser.getUsername().equals(username)) {
						retVal.setCommand("success");
						user.setLoggedIn(false);
						DBMS.getInstance().getSessionUsers().remove(user);
						// Ubaciti u logger  - TODO
						System.out.println("\t=> Izlogovao se korisnik - " + username);
						return retVal;
					}
				}	
			}
		}				
		
		return retVal;
	}
	
	public CommandMessage registerResponse(CommandMessage request) {
		UserBean user = ((UserBean) request.getObject());
		CommandMessage retVal = new CommandMessage();
		retVal.setCommand("failed");

				
		if (DBMS.getInstance().getUsersDatabase().contains(user)) {
			retVal.setCommand("existing user");
			return retVal;
		}
		
		// Ili ovo, ili DBMS.addUser..
		
		for (UserBean tempUser : DBMS.getInstance().getUsersDatabase()) {
			if (tempUser.getUsername().equals(user.getUsername())) {
				return retVal;
			}
		}
		
		user.setId(DBMS.getInstance().getUserID());
		DBMS.getInstance().getUsersDatabase().add(user);
		DBMS.getInstance().increaseUserID();
		DBMS.getInstance().saveUsers();
		DBMS.getInstance().saveDBMS();
		
		System.out.println("\t=> Kreiran korisnik - " + user.getUsername());
		
		retVal.setCommand("success");
		return retVal;
	}
	
	public CommandMessage currentCategoriesResponse(CommandMessage request) {
		
		CommandMessage retVal = new CommandMessage();
		retVal.setCommand("failed");
		
		ArrayList<CategoryBean> tempCategories = DBMS.getInstance().getCategoriesDatabase();
		
		if (tempCategories.isEmpty()) {
			retVal.setCommand("no categories ATM!");
			
			System.out.println("\t=> Failure! Ne postoje kategorije u bazi trenutno!");
			return retVal;
		} else {
			retVal.setCommand("success");
			retVal.setObject(tempCategories);
			
			System.out.println("\t=> Klijentu prosledjeno kategorija :" + tempCategories.size());
			return retVal;
		}
		
	}
	
	public CommandMessage createCategoryResponse(CommandMessage request) {
		CategoryBean category = ((CategoryBean) request.getObject());
		CommandMessage retVal = new CommandMessage();
		retVal.setCommand("failed");

				
		if (DBMS.getInstance().getCategoriesDatabase().contains(category)) {
			retVal.setCommand("duplicate");
			return retVal;
		}
		
		// Ili ovo, ili DBMS.addUser..
			
		category.setId(DBMS.getInstance().getCategoriesID());
		DBMS.getInstance().getCategoriesDatabase().add(category);
		DBMS.getInstance().increaseCategoriesID();
		DBMS.getInstance().saveCategories();
		DBMS.getInstance().saveDBMS();
		
		System.out.println("\t=> Kreirana kategorija - " + category.getName());
		System.out.println(DBMS.getInstance().getCategoriesDatabase().get(
				DBMS.getInstance().getCategoriesDatabase().size()-1).getSubcategory().getName());
		
		retVal.setCommand("success");
		return retVal;
	}
	
	public CommandMessage savePictureResponse(CommandMessage request) throws Exception {
		UploadedFileBean file = ((UploadedFileBean) request.getObject());
		CommandMessage retVal = new CommandMessage();
		retVal.setCommand("failed");
		
		FileItem item = (FileItem) file.getFile();
		File uploadedFile = new File("/resources/" + file.getFileName());
		item.write(uploadedFile);
		
		System.out.println("\t=> gg wp ");
		
		retVal.setObject((Object) uploadedFile.getAbsolutePath());
		retVal.setCommand("success");
		return retVal;
	}
	
	public CommandMessage createComponentResponse(CommandMessage request) {
		ComponentBean component = ((ComponentBean) request.getObject());
		CommandMessage retVal = new CommandMessage();
		retVal.setCommand("failed");

				
		if (DBMS.getInstance().getComponentsDatabase().contains(component)) {
			retVal.setCommand("duplicate");
			return retVal;
		}
			
		component.setId(DBMS.getInstance().getComponentID());
		DBMS.getInstance().getComponentsDatabase().add(component);
		DBMS.getInstance().increaseComponentID();
		DBMS.getInstance().saveComponents();
		DBMS.getInstance().saveDBMS();
		
		System.out.println("\t=> Kreirana komponenta - " + component.getName() + "/ " + component.getCategory().getName() + "/ " + component.getId()
				+ "/ " + component.getPrice());
		
		retVal.setCommand("success");
		return retVal;
	}
	
	public CommandMessage currentComponentsResponse(CommandMessage request) {
		
		CommandMessage retVal = new CommandMessage();
		retVal.setCommand("failed");
		
		ArrayList<ComponentBean> tempComponents = DBMS.getInstance().getComponentsDatabase();
		
		if (tempComponents.isEmpty()) {
			retVal.setCommand("no components ATM!");
			
			System.out.println("\t=> Failure! Ne postoje komponente u bazi trenutno!");
			return retVal;
		} else {
			retVal.setCommand("success");
			retVal.setObject(tempComponents);
			
			System.out.println("\t=> Klijentu prosledjeno komponenti :" + tempComponents.size());
			return retVal;
		}
		
	}
	
	public CommandMessage currentDevicesResponse(CommandMessage request) {
		
		CommandMessage retVal = new CommandMessage();
		retVal.setCommand("failed");
		
		ArrayList<DeviceBean> tempDevices = DBMS.getInstance().getDevicesDatabase();
		
		if (tempDevices.isEmpty()) {
			retVal.setCommand("empty");
			System.out.println("\t=> Failure! Ne postoje uredjaji u bazi trenutno!");
			return retVal;
		} else {
			retVal.setCommand("success");
			retVal.setObject(tempDevices);
			
			System.out.println("\t=> Klijentu prosledjeno uredjaja :" + tempDevices.size());
			return retVal;
		}
		
	}
	
	public CommandMessage createDeviceResponse(CommandMessage request) {
		DeviceBean device = ((DeviceBean) request.getObject());
		CommandMessage retVal = new CommandMessage();
		retVal.setCommand("failed");

				
		if (DBMS.getInstance().getDevicesDatabase().contains(device)) {
			retVal.setCommand("duplicate");
			return retVal;
		}
			
		device.setId(DBMS.getInstance().getDeviceID());
		DBMS.getInstance().getDevicesDatabase().add(device);
		DBMS.getInstance().increaseDeviceID();
		DBMS.getInstance().saveDevices();
		DBMS.getInstance().saveDBMS();
		
		System.out.println("\t=> Kreiran uredjaj - " + device.getName() + "/ id =" + device.getId());
		
		retVal.setCommand("success");
		return retVal;
	}

	
/*	public Poruka addAuthorResponse(Poruka request) {
		Poruka retVal = new Poruka();
		retVal.setCommand("failed");

		Autor novi = (Autor) request.getObject();

		if (novi != null) 
			if (Galerija.getInstance().getKolekcijaAutora().get(novi.getId()) == null){

			Galerija.getInstance();

			Galerija.getInstance().getKolekcijaAutora().put(novi.getId(), novi);
			retVal.setCommand("success");
			Galerija.getInstance().saveAutor();
			
			return retVal;

		} 
		
		return retVal;
	}
	
	public Poruka findAuthorsResponse(Poruka request){
		Poruka retVal = new Poruka();
		retVal.setCommand("success");
		String tmp = (String) request.getObject();
		
		StringTokenizer st = new StringTokenizer(tmp, "|");
		String _id = st.nextToken().trim();
		String _ime = st.nextToken().trim();
		String _prezime = st.nextToken().trim();
		retVal.setObject(Galerija.getInstance().findAuthors(_id, _ime, _prezime));
		
		return retVal;
	}
	//slika
	public Poruka addPictureResponse(Poruka request) {
		Poruka retVal = new Poruka();
		retVal.setCommand("failed");

		Slika novi = (Slika) request.getObject();

		if (novi != null) 
			if (Galerija.getInstance().getSlike().get(novi.getId()) == null){	
			Galerija.getInstance();

			Galerija.getInstance().getKolekcijaUmetnickihDela().put(novi.getId(), novi);
			retVal.setCommand("success");
			Galerija.getInstance().saveUmetnickoDelo();
			
			return retVal;

		}
		return retVal;
	}

	public Poruka getAuthorsResponse(Poruka request) {
		Poruka retVal = new Poruka();
		

		retVal.setCommand("success");
		List<Autor> temp = new ArrayList<Autor>(Galerija.getInstance().getKolekcijaAutora().values());
		retVal.setObject(temp);

		
		return retVal;

	
	}
	//slike
	public Poruka getPicturesResponse(Poruka request) {
		Poruka retVal = new Poruka();
		

		retVal.setCommand("success");
		List<Slika> temp = new ArrayList<Slika>(Galerija.getInstance().getSlike().values());
		retVal.setObject(temp);

		
		return retVal;

	
	}
	
	public Poruka deleteAutorResponse(Poruka request) {
		Poruka retVal = new Poruka();
		Autor a = (Autor)request.getObject();

		retVal.setCommand("success");
		Galerija.getInstance().getKolekcijaAutora().remove(a.getId());
		List<Autor> temp = new ArrayList<Autor>(Galerija.getInstance().getKolekcijaAutora().values());
		retVal.setObject(temp);

		
		return retVal;

	
	}
	
	public Poruka deletePictureResponse(Poruka request) {
		Poruka retVal = new Poruka();
		Slika a = (Slika)request.getObject();

		retVal.setCommand("success");
		Galerija.getInstance().getKolekcijaUmetnickihDela().remove(a.getId());
		List<Slika> temp = new ArrayList<Slika>(Galerija.getInstance().getSlike().values());
		retVal.setObject(temp);

		
		return retVal;

	
	}
	
	public Poruka editAutorResponse(Poruka request) {
		Poruka retVal = new Poruka();
		Autor temp = (Autor)request.getObject();

		retVal.setCommand("success");
		Galerija.getInstance().getKolekcijaAutora().remove(temp.getId());
		Galerija.getInstance().getKolekcijaAutora().put(temp.getId(), temp);
		retVal.setObject("");

		
		return retVal;

	
	}
	
	public Poruka editPictureResponse(Poruka request) {
		Poruka retVal = new Poruka();
		Slika temp = (Slika)request.getObject();

		retVal.setCommand("success");
		Galerija.getInstance().getSlike().remove(temp.getId());
		Galerija.getInstance().getSlike().put(temp.getId(), temp);
		retVal.setObject("");

		
		return retVal;

	
	}*/
}
