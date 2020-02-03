package database.insert;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class Spicies {
	public static final Logger LOGGER = Logger.getLogger(Spicies.class);
	
	private static final String TAMABLE ="Achatina|Allosaurus|Angler|Ankylosaurus|Araneo|Archaeopteryx|Argentavis|Arthropluera|Baryonyx|Basilisk|Basilosaurus|Beelzebufo|Brontosaurus|Bulbdog|Carbonemys|Carnotaurus|Castoroides|Chalicotherium|Coelacanth|Compy|Daeodon|Deinonychus|Desert Titan|Dilophosaur|Dimetrodon|Dimorphodon|Diplocaulus|Diplodocus|Direbear|Direwolf|Dodo|Doedicurus|Dung Beetle|Dunkleosteus|Electrophorus|Enforcer|Equus|Featherlight|Forest Titan|Gallimimus|Gasbags|Giant Bee|Giganotosaurus|Gigantopithecus|Glowtail|Griffin|Hesperornis|Hyaenodon|Ice Titan|Ichthyornis|Ichthyosaurus|Iguanodon|Jerboa|Kairuku|Kaprosuchus|Karkinos|Kentrosaurus|Lamprey|Leech|Liopleurodon|Lymantria|Lystrosaurus|Mammoth|Managarmr|Manta|Mantis|Megalania|Megaloceros|Megalodon|Megalosaurus|Megatherium|Mesopithecus|Microraptor|Morellatops|Mosasaurus|Moschops|Onyc|Otter|Oviraptor|Ovis|Pachy|Pachyrhinosaurus|Paraceratherium|Parasaur|Pegomastax|Pelagornis|Phiomia|Phoenix|Piranha|Plesiosaur|Procoptodon|Pteranodon|Pulmonoscorpius|Purlovia|Quetzal|Raptor|Ravager|Reaper|Rex|Rock Drake|Rock Elemental|Roll Rat|Royal Griffin|Sabertooth|Sabertooth Salmon|Sarco|Shinehorn|Snow Owl|Spinosaurus|Stegosaurus|Tapejara|Terror Bird|Therizinosaurus|Thorny Dragon|Thylacoleo|Titanoboa|Titanosaur|Triceratops|Trilobite|Troodon|Tusoteuthis|Unicorn|Velonasaur|Vulture|Woolly Rhino|Wyvern|Yutyrannus|Abberant Coelacanth|Aberrant Achatina|Aberrant Dimetrodon|Aberrant Dimorphodon|Aberrant Dodo|Aberrant Dung Beetle|Aberrant Electrophorus|Aberrant Lystrosaurus|Aberrant Otter|Aberrant Piranha|Aberrant Purlovia|Aberrant Salmon|Aberrant Titanoboa|Aberrant Trilobite|Aberrant Anglerfish|Aberrant Ankylosaurus|Aberrant Araneo|Aberrant Arthropluera|Aberrant Baryonyx|Aberrant Beelzebufo|Aberrant Carbonemys|Aberrant Carnotaurus|Aberrant Diplocaulus|Aberrant Diplodocus|Aberrant Dire Bear|Aberrant Doedicurus|Aberrant Equus|Aberrant Gigantopithecus|Aberrant Iguanodon|Aberrant Manta|Aberrant Megalania|Aberrant Megalosaurus|Aberrant Moschops|Aberrant Ovis|Aberrant Paraceratherium|Aberrant Parasaur|Aberrant Pulmonoscorpius|Aberrant Raptor|Aberrant Sarco|Aberrant Spino|Aberrant Stegosaurus|Aberrant Triceratops";
	private static final String NONTAMABLE ="Ammonite|Attack Drone|Broodmother Lysrix|Cnidaria|Deathworm|Defense Unit|Dragon|Eurypterid|Glowbug|Jug Bug|King Titan|Manticore|Meganeura|Megapithecus|Nameless|Overseer|Seeker|Titanomyrma|Yeti|Aberrant Cnidaria|Aberrant Meganeura";
	private static final String RIDEABLE = "Allosaurus|Angler|Ankylosaurus|Araneo|Argentavis|Arthropluera|Baryonyx|Basilisk|Basilosaurus|Beelzebufo|Brontosaurus|Carbonemys|Carnotaurus|Castoroides|Chalicotherium|Daeodon|Deinonychus|Desert Titan|Diplocaulus|Diplodocus|Direbear|Direwolf|Doedicurus|Dunkleosteus|Enforcer|Equus|Forest Titan|Gallimimus|Gasbags|Giganotosaurus|Gigantopithecus|Griffin|Ice Titan|Ichthyosaurus|Iguanodon|Kaprosuchus|Karkinos|Liopleurodon|Lymantria|Mammoth|Managarmr|Manta|Mantis|Megalania|Megaloceros|Megalodon|Megalosaurus|Mega Mek|Megatherium|Mek|Morellatops|Mosasaurus|Moschops|Ovis|Pachy|Pachyrhinosaurus|Paraceratherium|Parasaur|Pelagornis|Phiomia|Phoenix|Plesiosaur|Procoptodon|Pteranodon|Pulmonoscorpius|Quetzal|Raptor|Ravager|Reaper|Rex|Rock Drake|Rock Elemental|Roll Rat|Royal Griffin|Sabertooth|Sarco|Snow Owl|Spinosaurus|Stegosaurus|Tapejara|Terror Bird|Therizinosaurus|Thorny Dragon|Thylacoleo|Titanosaur|Triceratops|Tusoteuthis|Unicorn|Velonasaur|Woolly Rhino|Wyvern|Yutyrannus|Aberrant Anglerfish|Aberrant Ankylosaurus|Aberrant Araneo|Aberrant Arthropluera|Aberrant Baryonyx|Aberrant Beelzebufo|Aberrant Carbonemys|Aberrant Carnotaurus|Aberrant Diplocaulus|Aberrant Diplodocus|Aberrant Dire Bear|Aberrant Doedicurus|Aberrant Equus|Aberrant Gigantopithecus|Aberrant Iguanodon|Aberrant Manta|Aberrant Megalania|Aberrant Megalosaurus|Aberrant Moschops|Aberrant Ovis|Aberrant Paraceratherium|Aberrant Parasaur|Aberrant Pulmonoscorpius|Aberrant Raptor|Aberrant Sarco|Aberrant Spino|Aberrant Stegosaurus|Aberrant Triceratops";
	private static final String BREEDABLE = "Allosaurus|Angler|Ankylosaurus|Archaeopteryx|Argentavis|Baryonyx|Basilosaurus|Beelzebufo|Brontosaurus|Bulbdog|Carbonemys|Carnotaurus|Castoroides|Chalicotherium|Compy|Daeodon|Deinonychus|Dilophosaur|Dimetrodon|Dimorphodon|Diplocaulus|Diplodocus|Direbear|Direwolf|Dodo|Doedicurus|Dunkleosteus|Electrophorus|Equus|Featherlight|Gallimimus|Gasbags|Giganotosaurus|Gigantopithecus|Glowtail|Hesperornis|Hyaenodon|Ichthyornis|Ichthyosaurus|Iguanodon|Jerboa|Kairuku|Kaprosuchus|Kentrosaurus|Leedsichthys|Lystrosaurus|Mammoth|Managarmr|Manta|Megalania|Megaloceros|Megalodon|Megalosaurus|Megatherium|Mesopithecus|Microraptor|Morellatops|Mosasaurus|Moschops|Otter|Oviraptor|Ovis|Pachy|Pachyrhinosaurus|Paraceratherium|Parasaur|Pegomastax|Pelagornis|Phiomia|Plesiosaur|Procoptodon|Pteranodon|Purlovia|Quetzal|Raptor|Ravager|Reaper|Rex|Roll Rat|Sabertooth|Sarco|Shinehorn|Snow Owl|Spinosaurus|Stegosaurus|Tapejara|Terror Bird|Therizinosaurus|Thorny Dragon|Thylacoleo|Triceratops|Troodon|Tusoteuthis|Unicorn|Vulture|Woolly Rhino|Yutyrannus|Aberrant Anglerfish|Aberrant Ankylosaurus|Aberrant Baryonyx|Aberrant Beelzebufo|Aberrant Carbonemys|Aberrant Carnotaurus|Aberrant Diplocaulus|Aberrant Diplodocus|Aberrant Dire Bear|Aberrant Doedicurus|Aberrant Equus|Aberrant Gigantopithecus|Aberrant Iguanodon|Aberrant Manta|Aberrant Megalania|Aberrant Megalosaurus|Aberrant Moschops|Aberrant Ovis|Aberrant Paraceratherium|Aberrant Parasaur|Aberrant Raptor|Aberrant Sarco|Aberrant Spino|Aberrant Stegosaurus|Aberrant Triceratops|Aberrant Dimetrodon|Aberrant Dimorphodon|Aberrant Dodo|Aberrant Electrophorus|Aberrant Lystrosaurus|Aberrant Otter|Aberrant Purlovia";
		/*
	private static final String A = ""+
			"FebruaryDecember";
	
	public static void convert()
	{
		StringBuilder sb = new StringBuilder(A);
		int a = 0;
		int b;
		System.out.print("|");
		while (a<sb.lastIndexOf("Aberrant "))
		{
			if (sb.indexOf("December",a)<sb.indexOf("February",a)) {b = sb.indexOf("December",a);}else {b = sb.indexOf("February",a);}
			System.out.print(sb.substring(a, b).trim()+"|");
			a= sb.indexOf("Aberrant ",a+9);
		}
		if (sb.indexOf("December",a)<sb.indexOf("February",a)) {b = sb.indexOf("December",a);}else {b = sb.indexOf("February",a);}
		System.out.print(sb.substring(a, b).trim());
	}
	*/
	
	
	private Spicies() {}
	
	public static void insertSpicies()
	{
		try (Statement st = DriverManager.getConnection(init.Main.CONN).createStatement();
				BufferedReader in = new BufferedReader(new FileReader(".\\config\\dinoSpicies.txt"));)
		{
			String s = "INSERT INTO spicies("+in.readLine()+") VALUES(";
			while(true)
			{
				String t = in.readLine();
				if(t ==null)
				{
					break;
				}
				else
				{
					st.execute(s+t+")");
				}
			}
			
		} catch (SQLException | IOException e) {
			LOGGER.error(e);
		}
	}
	
	
	
	/*
	public static void insertSpicies()
	{
		LOGGER.debug("Started inserting.");
		int a = 0;
		try (Statement st = DriverManager.getConnection(init.Main.CONN).createStatement();)
		{
			StringBuilder sb = new StringBuilder(TAMABLE);
			while (a-1!=sb.lastIndexOf("|"))
			{
				st.execute(insertTamableIntoSpicies(sb.substring(a, sb.indexOf("|",a)),true));
				a= sb.indexOf("|",a)+1;
			}
			st.execute(insertTamableIntoSpicies(sb.substring(a, sb.length()),true));
			LOGGER.info("TAMABLE inserted.");
			a = 0;
			sb = new StringBuilder(NONTAMABLE);
			while (a-1!=sb.lastIndexOf("|"))
			{
				st.execute(insertTamableIntoSpicies(sb.substring(a, sb.indexOf("|",a)),false));
				a= sb.indexOf("|",a)+1;
			}
			st.execute(insertTamableIntoSpicies(sb.substring(a, sb.length()),false));
			LOGGER.info("NONTAMABLE inserted.");
			a = 0;
			sb = new StringBuilder(RIDEABLE);
			while (a-1!=sb.lastIndexOf("|"))
			{
				st.execute(updateMakeRideable(sb.substring(a, sb.indexOf("|",a))));
				a= sb.indexOf("|",a)+1;
			}
			st.execute(updateMakeRideable(sb.substring(a, sb.length())));
			LOGGER.info("RIDEABLE updated.");
			a = 0;
			sb = new StringBuilder(BREEDABLE);
			while (a-1!=sb.lastIndexOf("|"))
			{
				st.execute(updateMakeBreedable(sb.substring(a, sb.indexOf("|",a))));
				a= sb.indexOf("|",a)+1;
			}
			st.execute(updateMakeBreedable(sb.substring(a, sb.length())));
			LOGGER.info("BREEDABLE updated.");
			//TODO aliasis
		}catch (SQLException e) 
		{
			LOGGER.error("Error at "+a);
			LOGGER.error(e);
		}
		LOGGER.debug("Sucsesfully inserted.");
	}*//*
	private static String insertTamableIntoSpicies(String name,boolean tamable)
	{
		return "INSERT INTO spicies(name,tamable) VALUES('"+name+"',"+tamable+")";
	}
	private static String updateMakeRideable(String name)
	{
		return "UPDATE spicies SET Rideable = true WHERE Name = '"+name+"'";
	}
	private static String updateMakeBreedable(String name)
	{
		return "UPDATE spicies SET Breedable = true WHERE Name = '"+name+"'";
	}

	*/

}
