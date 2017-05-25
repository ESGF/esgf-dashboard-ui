package org.esg.node.datausage.actions.json;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import org.esg.node.datausage.bean.Continent;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author CMCC
 */

public class LoadContinentsFromFileAction extends ActionSupport {
	
	private static final long serialVersionUID = 3042938827121989637L;
	private List<Continent> continentlist = null;
	
	
	public String execute() throws Exception {
		continentlist = new LinkedList<Continent>();
		
		Scanner scanner = null;
		
		try {
			scanner = new Scanner(new File("/esg/config/desktop/planb_continents.txt"));
			scanner.useDelimiter("\n");
			Continent continent = new Continent();
			continent.setContinentname("all");
			continentlist.add(continent);
			while (scanner.hasNext()) {
				String continentname = scanner.nextLine();
				continent = new Continent();
				continent.setContinentname(continentname);
				continentlist.add(continent);
			}
		}
		catch (FileNotFoundException ex) {
			Continent all = new Continent();
			all.setContinentname("all");
			Continent asia = new Continent();
			asia.setContinentname("Asia");
			Continent africa = new Continent();
			africa.setContinentname("Africa");
			Continent europe = new Continent();
			europe.setContinentname("Europe");
			Continent northamerica = new Continent();
			northamerica.setContinentname("North America");
			Continent oceania = new Continent();
			oceania.setContinentname("Oceania");
			Continent southamerica = new Continent();
			southamerica.setContinentname("South America");
		    continentlist.add(all);
		    continentlist.add(asia);
		    continentlist.add(africa);
		    continentlist.add(europe);
		    continentlist.add(northamerica);
		    continentlist.add(oceania);
		    continentlist.add(southamerica);
		}
		finally {
			if(scanner != null)
				scanner.close();
		}
		
		return SUCCESS;
	}

	public List<Continent> getContinentlist() {
		return continentlist;
	}
}
