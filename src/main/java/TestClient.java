import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.client.JerseyInvocation;
import org.glassfish.jersey.client.JerseyWebTarget;

import dao.*;
import interfaces.Mapper;
import objects_misc.ReactionCount;
import objects_table.*;
import utilities.*;

public class TestClient {

	public static void main(String[] args) {
		webClient();
	}
	
	static void populateReactions() {
		ArrayList<Announcement> aal = (ArrayList)AnnouncementDAO.getInstance().getAll();
		ArrayList<User> ual = (ArrayList)UserDAO.getInstance().getAll();
		
		for(User u: ual) {
			ArrayList<Reaction> avail = CollectiveUtility.getAvailableReactions(u.getId());
			for(Announcement a : aal) {
				for(Reaction r: avail) {
					int roll = (int)(Math.random() * 2);
					boolean flip = false;
					if(roll > 0) flip = true;
					
					if(flip) {
						PostUserReaction pur = new PostUserReaction();
						pur.setPost_source("announcements");
						pur.setPost_id(a.getId());
						pur.setUser(u.getId());
						pur.setReaction(r.getId());
						PostUserReactionDAO.getInstance().insertNew(pur);
					}
					
				}
			}
		}
	}
	
	static void reactionLink() {
		ArrayList<Reaction> ral = (ArrayList)ReactionDAO.getInstance().getAll();

		for(Reaction r: ral) {
			long owner = r.getOwner();
			if(owner == 0) {
				continue;
			}
			
			ArrayList<GroupUser> gul = (ArrayList)GroupUserDAO.getInstance().getByComponent(owner, 1);
			
			long[] gids = new long[gul.size()];
			for(int i = 0; i < gul.size(); i++) {
				gids[i] = gul.get(i).getGroup();
			}
			
			ArrayList<Group> gal = (ArrayList)GroupDAO.getInstance().getSet(gids);
			
			for(Group g: gal) {
				boolean apply = false;
				if((int)(Math.random() * 2) == 1){
					apply = true;
				}
				
				if(apply) {
					GroupReaction gr = new GroupReaction();
					gr.setGroup(g.getId());
					gr.setReaction(r.getId());
					GroupReactionDAO.getInstance().insert(gr);
				}
			}
		}
	}
	
	static void groupSetup() {
		/*
		GroupUser gu1 = new GroupUser();
		gu1.setGroup(100);
		gu1.setUser(100);
		GroupUserDAO.getInstance().insert(gu1);
		
		GroupUser gu2 = new GroupUser();
		gu2.setGroup(100);
		gu2.setUser(101);
		GroupUserDAO.getInstance().insert(gu2);	
		

		GroupUser gu3 = new GroupUser();
		gu3.setGroup(101);
		gu3.setUser(102);
		GroupUserDAO.getInstance().insert(gu3);
		
		GroupUser gu4 = new GroupUser();
		gu4.setGroup(102);
		gu4.setUser(103);
		GroupUserDAO.getInstance().insert(gu4);
		*/
		
		GroupUser gu5 = new GroupUser();
		gu5.setGroup(102);
		gu5.setUser(102);
		GroupUserDAO.getInstance().insert(gu5);
	}
	
	static void generateReplies() {
		ArrayList<String> quotes = new ArrayList<String>();
		
		quotes.add("Trust your hunches. They're usually based on facts filed away just below the conscious level.");
		quotes.add("But where only a free play of our presentational powers is to be sustained as in the case of pleasure gardens, room decoration, all sorts of useful utensils, and so on, any regularity that has an air of constraint is to be avoided as much as possible. That is why the English taste in gardens, or the baroque taste in furniture, carries the imagination's freedom very far, even to the verge of the grotesque, because it is precisely this divorce from any constraint of a rule that the case is posited where taste can show its greatest perfection in designs made by the imagination.");
		quotes.add("Contemplating an object fixedly with the mind, asking myself, 'What is it?' without thinking of any other object or relating it to anything else for hours on end.");
		quotes.add("Lots of people want to ride with you in the limo, but what you want is someone who will take the bus with you when the limo breaks down.");
		quotes.add("For tribal man, space was the uncontrollable mystery. For technological man it is time that occupies the same role.");
		quotes.add("We have now armed to such an extent as the world has never before seen.");
		quotes.add("If you want to know how rich you really are, find out what would be left of you tomorrow if you should lose every dollar you own tonight.");
		quotes.add("If there is any one axiom that I have tried to live up to in trying to become successful in business, it is the fact that I have tried to surround myself with associates that know more about business than I do. This policy has always been very successful and is still working for me.");
		quotes.add("Wisdom is the power that enables us to use knowledge for the benefit of ourselves and others.");
		quotes.add("Sometimes the road less traveled is less traveled for a reason.");
		quotes.add("A wicked tongue sets England and France Fighting each other with spear and lance. A wicked tongue can break a bone Though a tongue itself has none.");
		quotes.add("Get your facts first, then you can distort them as you please.");
		quotes.add("No man will make a great leader who wants to do it all himself, or to get all the credit for doing it.");
		
		for(String quote: quotes) {
			Reply r = new Reply();
			r.setAuthor((int)(Math.random()*80 + 100));
			r.setContent(quote);
			r.setSource("announcements");
			r.setSource_id((int)(Math.random()*3 + 100));
			r.setTimestamp(generateRandomTimestamp());
			
			ReplyDAO.getInstance().insertNew(r);
		}
	}
	
	static java.sql.Timestamp generateRandomTimestamp() {
		Calendar cal = Calendar.getInstance();
		int rand_yr = (int)(Math.random()*3 + 2018);
		int rand_mn = (int)(Math.random()*12);
		int rand_dt = (int)(Math.random()*24);
		int rand_hr = (int)(Math.random()*24);
		int rand_mi = (int)(Math.random()*60);
		int rand_sc = (int)(Math.random()*60);

		cal.set(rand_yr, rand_mn, rand_dt, rand_hr, rand_mi, rand_sc);

		return new java.sql.Timestamp(cal.getTimeInMillis());
	}
	
	public static void webClient() {
		JerseyClient client;
		JerseyWebTarget webTarget;
		JerseyInvocation.Builder invocationBuilder;
		Response response;
		String output;

		client = JerseyClientBuilder.createClient();

		webTarget = client.target("http://localhost:8080/collective-backend/api").path("messages/announcements/replies/100");

		invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

		//invocationBuilder.header("x-auth", value)
		String body = "{\"user_id\":100}";

		response = invocationBuilder.get();
		
		//response = invocationBuilder.post(Entity.entity(body, MediaType.APPLICATION_JSON));

		//System.out.println(response);
		
		if (response.getStatus()/100 != 2) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}

		System.out.println("Output from Server .... \n");
		output = response.readEntity(String.class);
		System.out.println(output);
	}

	public static void PrintTree(ArrayTree<String> tree) {
		System.out.println(tree.getData());
		for(ArrayTree node: tree.getNodes()) {
			PrintTree(node);
		}
	}
	
	static void generateArtMeta() {
		File myFile = new File("lines.txt");
		FileWriter myWriter = null;
		try {
			myWriter = new FileWriter(myFile);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int[] artists = new int[] {100, 103, 105, 108, 116, 119, 120, 122, 123, 126, 127, 129, 130, 134, 137, 139, 142, 143, 147, 148, 155, 158, 160, 162, 165, 166, 169, 170, 174, 178};
		int[] shops = new int[] {100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229};
		String[] titles = new String[] {"Foreigner Of New Earth", "Commander Of Death", "Figure Of Our Legacy", "Defender Of Eternity", "Doctor Of The Flight", "Friend Of Earth", "Volunteer Of Life", "Android From The Portal", "Child Of The Past", "Mercenary Of New Earth", "Soldier Of Nowhere", "Guest Of The Dead", "Soldier Of Exploration", "Martian In The News", "Pilot Of The Stars", "Child Of The Dead", "Recruit Of The Worlds", "Emperor Of Darkness", "Child In The Portal", "Defender Of Moondust", "Alien Of Our Legacy", "Robot On Mars", "Veteran Of Our Ship", "Leader Of Eternity", "Director Of The Flight", "Emperor Of Death", "Soldier Of Our Destiny", "Droid In The Portal", "Pilot Of Outer Space", "Director Of Stardust", "Hero Of The Stars", "Mercenary Of The Ocean", "Veteran Of Eternity", "Hero From Outer Space", "Pilot Of Honor", "Leader Of Earth's Legacy", "Invader Of Everywhere", "Rebel Of Stardust", "Doctor Of The Universe", "Invader In The Portal", "Rebel", "Spy With A UFO", "Veteran Of Darkness", "Cyborg From The Portal", "Creature With A Spaceship", "Child Of Men's Legacy", "Assassin Of Our Future", "Beast Of The Moon", "Agents From The Portal", "Boys Of Our Legacy", "Guests Of Our Legacy", "Hunters Of Aliens", "Creators Of The Crash", "Men With Spaceships", "Pilots Of The New World", "Creatures Of Moondust", "Cyborgs From Outer Space", "Boys Of Death", "Directors Of The Worlds", "Soldiers With Four Eyes", "Agents Of The Sun", "Droids Of War", "Visitors Of Nowhere", "Captains Of The Outlands", "Captains Of Earth", "Medics From The Portal", "Androids Of The Vacuum", "Recruits Of New Earth", "Intruders Of The Past", "Strangers Of The Crash", "Mercenaries Of War", "Strangers On Mars", "Commanders Of The Sun", "Boys Of Stardust", "Figures Of Mars", "Assassins Of The Universe", "Of The Past", "Humans Of Our Destiny", "Visitors In The Future", "Soldiers Of The Galaxy", "Of Stardust", "Pilots Of Outer Space", "Rebels Of Moondust", "Doctors Of The Flight", "Of The Dead", "Mercenaries In The Past", "Directors With Spaceships", "Martians Of Moondust", "Hunters Of Earth's Legacy", "Androids", "Rebels Of Aliens", "Foreigners Of New Worlds", "Intruders On Mars", "Men Of Stardust", "Martians Of Nowhere", "Intruders From The Portal", "And Droids", "Mercenaries And Rebels", "Strangers And Aliens", "Boys And Strangers", "Creators And Soldiers", "Humans And Heroes", "Figures And Creators", "Armies And", "Aliens And Beasts", "And Recruits", "Creatures And Figures", "Humans And Spies", "Enemies And Clones", "Emperors And Androids", "Martians And Women", "Officers And Martians", "Directors And Foreigners", "Foreigners And Clones", "Friends And Hunters", "Androids And Martians", "Figures And Invaders", "Commanders And Robots", "Soldiers And Cyborgs", "Robots And Strangers", "Enemies And Robots", "Men And Men", "Commanders And Commanders", "Guardians And Intruders", "Figures And Spies", "Men And Assassins", "Robots And Emperors", "Pilots And Boys", "Recruits And Clones", "Directors And Creatures", "Pilots And Droids", "Visitors And Captains", "Volunteers And Captains", "Women And Recruits", "Figures And Recruits", "Men And Robots", "Intruders And Directors", "Friends And Robots", "Guests And Visitors", "Figures And Visitors", "Recruits And Androids", "Agents And Invaders", "Foreigners And Pilots", "Officers And Creators", "Throne Of Alien Life", "Extinction Of The New Order", "End Of Robots", "Scourge Of Our Destiny", "Hope Of The Stars", "Carnage On Mars", "Monument Of The Past", "Result Of Men's Legacy", "Birth Of Men's Legacy", "Ascension Of The Flight", "Confinement Of Society", "Honor Of War", "Fate Of The Dead", "Hope Of Time", "Destiny Of New Life", "Chase Of Moondust", "Destiny Of The Galaxy", "Symbols Of The Invaders", "Ascension Of The Planet", "Ambush Of The Faceless Ones", "Monument Of Everywhere", "Hope Of New Life", "Star Of Our Culture", "Fate From Outer Space", "Honor Of The Crash", "Loss Of The New Order", "Throne Of Everywhere", "Monument Of Space", "Nation Of Life", "Element On Mars", "Exploration Of The Crash", "Creation Of The Ocean", "Chase Of Everywhere", "Monuments Of Life", "Symbols Of Robots", "Restoration Of The Faceless Ones", "Hope Of Our Culture", "Root Of Stardust", "Love Of Our Destiny", "Extermination Of Honor", "Ruins Of Death", "Scourge Of The Planet", "Creation Of The Aliens", "Deception Of The Vacuum", "Nation Of The Future", "Ruins Of Eternity", "Carvings Of Darkness", "Scourge Of Nowhere", "Cautious Of New Earth", "Experience Of Electricity", "Intelligence In My Space Journey", "Life After My Planet", "Fortune Of The Eyes", "Intelligence In Moon Rocks", "Origin Of Eternity", "Darkness Of The New Gods", "Changed By My Android Servant", "Father Of Technology", "Joy Of The Portal", "Created By Space Flight", "Dependent On First Contact", "Failure Of The End Of The Sun", "Celebrating Solar Flares", "Married To Technolic Advancements", "Haunted By Orbit", "Fixed In Outer Space", "Fixed In My Space Journey", "Fortune Of Androids", "Longing For My Space Journey", "Confused By The Mists", "Devoted To The Legends", "Demand For The Intruders", "Mother Of The Troopers", "Admiration For The End Of The Sun", "Hidden By Robots", "Right For A Nuclear War", "Failure Of Technolic Advancements", "Fortune Of The New Planet", "Lonely In Solar Flares", "Better Technolic Advancements", "Inspired By The Moon", "Life With My Journey", "Secrets Of Orbit", "Created By The New Gods", "Favor Of The Immortals", "Demand For The Void", "Changed By The New World", "Fixed In A Rise Of Machines", "Glory Of The Guests", "Frightened Of My Space Journey", "Serenity Of The Depths", "Courage For The Aliens", "Puzzle Of The Stars", "Broken The Mists", "Celebrating Orbit", "Alive In The Troopers", "Invader Of The Galaxy", "Droid Of The Flight", "Friend Of New Earth", "Intruder Of Exploration", "Robot Of Our Destiny", "Medic Of The New World", "Droid Of Our Ship", "Stranger Of Nowhere", "Beast Of The Ocean", "Stranger Of Our Ship", "Defender Of Exploration", "Assassin With Spaceships", "Leader Of Aliens", "Veteran", "Human In The News", "Pilot Of Time", "Medic From Outer Space", "Hero Of Death", "Enemy Of Aliens", "Alien Of Darkness", "Alien In The Center Of The Earth", "Girl Of Space", "Intruder Of The Flight", "Stranger In The Beginning Of Time", "Martian In The Beginning Of Time", "Intruder", "Intruder In The Center Of The Earth", "Hunter Of The Void", "Spy Of Nowhere", "Commander Of The Worlds", "Assassin On Mars", "Hunter Of The Fallen", "Army Of War", "Woman Of Nowhere", "Creator Of The Sun", "Medic Of Earth", "Mercenary Of Time", "Man Of The Ocean", "Hero Of Moondust", "Android Of The Void", "Cyborg From The Portal", "Clone Of Our Ship", "Droid Of Men's Legacy", "Beast Of Darkness", "Creature Of The Crash", "Invader Of Space", "Director Of The Dead", "Clone Of The Worlds", "Directors Of Time", "Strangers With Tentacles", "Clones With Spaceships", "Soldiers Of Our Future", "Rebels Of Nowhere", "Creators Of The Sands", "Aliens Of Earth", "Heroes Of The Sands", "Visitors In The Beginning Of Time", "Clones Of The Sands", "Heroes Of The Sands", "Heroes Of Earth's Legacy", "Men Of Life", "Clones Of Mars", "Droids Of The Orbit", "Emperors From The Portal", "Humans Of The Vacuum", "Commanders Of New Worlds", "Robots Of Outer Space", "Men With Spaceships", "Heroes Of Our Destiny", "Traitors With Tentacles", "Children Of The Universe", "Spies Of War", "Children Of Outer Space", "Foreigners In The Past", "Droids Of Sunshine", "Guardians Of The Sun", "Assassins In The Portal", "Women Of The Future", "Androids Of The Ocean", "Assassins With A UFO", "Hunters Of New Earth", "Emperors Of Space", "Doctors Of The Moon", "Women Of Honor", "Veterans Of Honor", "Enemies Of Eternity", "Cyborgs Of The Vacuum", "Clones Of The Future", "Defenders From The UFO", "Boys From The UFO", "Hunters From The UFO", "Leaders From The Portal", "Directors With A Spaceship", "Assassins Of The Flight", "Visitors Of Time", "Women Of The Stars", "Girls And Doctors", "Traitors And Robots", "Droids And Spies", "Martians And Cyborgs", "Mercenaries And Androids", "Droids And Women", "Directors And Humans", "Figures And Intruders", "Children And Heroes", "Commanders And Officers", "Captains And Spies", "Enemies And Girls", "Friends And Guardians", "Soldiers And Emperors", "Martians And Recruits", "Strangers And Captains", "Boys And Pilots", "Women And Guardians", "Intruders And Leaders", "Pilots And Defenders", "Medics And Beasts", "Cyborgs And", "Men And Martians", "Hunters And Soldiers", "Commanders And Recruits", "Hunters And Emperors", "Leaders And Volunteers", "Soldiers And Armies", "Friends And Men", "Friends And Boys", "Friends And Directors", "Defenders And Hunters", "Androids And Creatures", "Androids And Men", "Strangers And Robots", "Volunteers And Hunters", "Veterans And Creators", "Humans And Hunters", "Veterans And Boys", "Enemies And Beasts", "Veterans And Volunteers", "Girls And Humans", "Veterans And Directors", "Invaders And Droids", "Volunteers And Androids", "Guardians And Figures", "Emperors And Rebels", "Women And Foreigners", "Appearance Of Aliens", "Betrayal Of The Worlds", "Root Of Aliens", "Extermination Of The Orbit", "Defeat Of Alien Life", "Disruption Of Honor", "Carvings Of The New Order", "Demise Of Darkness", "Monument Of The Orbit", "Inspiration Of The Dead", "Beginning Of Mars", "Star Of Alien Life", "Defeat Of Outer Space", "Statue Of The Galaxy", "Confinement Of Outer Space", "Beginning Of Life", "Statues Of Everywhere", "Birth Of New Life", "Construction Of The Droids", "Appearance Of Society", "Influence Of Our Destiny", "Inspiration Of The New World", "Ascension Of The Universe", "Result Of The Moon", "Love Of Our Legacy", "Culling Of The Moon", "Influence Of Eternity", "Origin Of The Sun", "Hatred Of Eternity", "Extinction Of The Moon", "Hatred Of The Crash", "Signs Of War", "Murder On Mars", "Culling Of The Sun", "Hatred Of Moondust", "Demise Of Earth's Legacy", "Influence Of The Universe", "Rise Of The Future", "Rebirth Of The Flight", "Destiny Of Our Legacy", "Victory Of The Androids", "Ascension Of The Dead", "Moon Of The Ocean", "End Of Honor", "Scourge Of The Sun", "Ruins Of Our Ship", "End Of The New World", "Doom Of Darkness", "Intelligence In The Fog", "Puzzle Of My Journey", "Longing For First Contact", "Created By Solar Flares", "Abandoned On Eternity", "Understanding The Portal", "Light Of New Technology", "Basic The New Gods", "Longing For First Contact", "Colors Of Technology", "Right For The Legends", "Gift Of The Portal", "Experience In The Aliens", "Blindd By The Sun", "Bored By The Depths", "Right For The Stars", "Abandoned In The Revolution", "Afraid Of The Secrets", "Sins Of Solar Flares", "Caution Of Time Travellers", "Father Of First Contact", "Life With New Earth", "Equality In The Sun", "Understanding My Planet", "Abandoned In My Journey", "Hidden In The Vacuum", "Open To New Earth", "Intelligence In Orbital Flight", "Joy Of The Secrets Of The Ocean", "Broken The Sun", "Courage For A Robot Takeover", "Life After Orbital Flight", "Open To The Portal", "Basic The Secrets", "Blindd By Orbital Flight", "Lonely In The Mists", "Experience In The End Of The Sun", "Security Of The Truth", "Devoted To The Vacuum", "Better Stardust", "Better The Robotic Police", "Cultured By Time Travellers", "Afraid Of A New War", "Crazy Of The Ocean", "Mother Of The Depths", "Glory Of Electricity", "Favor Of Outer Space", "Equality In Space Flight", "Soldier Of Earth's Legacy", "Army Of The Sands", "Visitor Of Death", "Man Of Honor", "Robot Of The Galaxy", "Emperor Of The Sands", "Visitor Of The Ocean", "Soldier From The UFO", "Captain Of Men's Legacy", "Angel With A Spaceship", "Commander Of War", "Visitor Of The Flight", "Defender Of Our Future", "Army Of Mars", "Robot Of Our Culture", "Intruder In The News", "Leader In The Past", "Volunteer With Spaceships", "Spy With A UFO", "Figure From The Portal", "Martian Of Earth's Legacy", "Woman With A UFO", "Beast Of Earth's Legacy", "Creator Of New Earth", "Hunter Of The Sands", "Traitor Of Earth's Legacy", "Figure Of The Fallen", "Captain In The Center Of The Earth", "Officer Of The New World", "Martian With Tentacles", "Army Of The New World", "Clone In The Center Of The Earth", "Volunteer Of Life", "Defender Of Earth's Legacy", "Rebel In The Center Of The Earth", "Friend Of The Past", "Guest Of The Universe", "Angel From The Portal", "Commander Of New Earth", "Volunteer Of Earth's Legacy", "Intruder Of Our Culture", "Intruder Of The Moon", "Man Of Our Legacy", "Pilot Of Space", "Alien Of The Future", "Visitor Of War", "Medic Of Aliens", "Spy In The News", "Defenders Of Aliens", "Friends On My Ship", "Strangers Of Our Destiny", "Guests Of Space", "Doctors With Four Eyes", "Androids With Tentacles", "Strangers", "Droids Of Everywhere", "Robots Of Sunshine", "Guests Of Everywhere", "Mercenaries Of The Vacuum", "Spies In The Beginning Of Time", "Medics Of Men's Legacy", "Strangers Of The Orbit", "Visitors In The Future", "Aliens Of Darkness", "Spies Of Everywhere", "Creatures In The Center Of The Earth", "Emperors Of Our Legacy", "Beasts In The Past", "Doctors Of Life", "Creators Of The Dead", "Boys With Four Eyes", "Martians Of Sunshine", "Guests Of Death", "Foreigners Of The Void", "Foreigners Of Earth", "Women From Outer Space", "Armies Of Our Culture", "Guardians", "Humans Of Stardust", "Commanders Of The Worlds", "Women Of Everywhere", "Beasts Of The Crash", "Creators Of The Void", "Aliens With Four Eyes", "Emperors Of The Vacuum", "Pilots Of The Stars", "Robots Of The Universe", "Friends Of The Ocean", "Intruders Of Exploration", "Hunters Of The Sun", "Captains Of Moondust", "Boys Of Darkness", "Emperors With A UFO", "Commanders Of The Outlands", "Beasts Of The Universe", "Doctors Of Eternity", "Traitors And Friends", "Foreigners And Beasts", "Martians And Friends", "Martians And Aliens", "Mercenaries And Foreigners", "Heroes And Heroes", "Strangers And", "Spies And Friends", "Spies And Figures", "Girls And Agents", "Volunteers And Intruders", "Creators And Doctors", "Clones And Friends", "Cyborgs And Directors", "Strangers And Intruders", "Figures And Pilots", "Aliens And Soldiers", "Strangers And Mercenaries", "Defenders And Humans", "Pilots And Agents", "And Defenders", "Heroes And Spies", "Cyborgs And Enemies", "Defenders And Boys", "Robots And Rebels", "Boys And Rebels", "Droids And Droids", "Assassins And Humans", "Recruits And Emperors", "Strangers And Men", "Volunteers And Cyborgs", "Figures And", "Beasts And Cyborgs", "Medics And Figures", "Soldiers And Friends", "Figures And Robots", "Officers And Visitors", "Men And", "Doctors And Volunteers", "Beasts And Boys", "Officers And Creators", "Traitors And Assassins", "Intruders And", "Leaders And Emperors", "Droids And Guests", "Aliens And Directors", "Rebels And Foreigners", "Directors And Traitors", "Planet", "Demise Of The Dead", "Statues Of The Moon", "Element Of Honor", "Love Of Alien Life", "Confinement Of War", "World Of New Life", "Betrayal Of The New Order", "Future On My Ship", "Result Of Earth", "World Of Society", "Extermination Of Our Destiny", "Statue Of The Aliens", "Moon Of The New Order", "Symbols Of War", "Influence Of The Intruders", "Throne Of The Universe", "Corruption Of Outer Space", "Hope Of The Droids", "Hope Of Honor", "Inception Of The New World", "Carnage Of The Dead", "World Of Mars", "Death Of The Aliens", "Construction Of Alien Life", "Influence Of Exploration", "Exploration Of Aliens", "Extinction Of Our Legacy", "Honor Of Everywhere", "Beginning Of Stardust", "Betrayal Of The Intruders", "Border Of Alien Life", "Future Of Our Culture", "Monuments Of Darkness", "Scourge On Mars", "Culling Of Society", "Nation Of Our Future", "World Of The Intruders", "Hope Of Men's Legacy", "Chase Of The New World", "Death Of The New World", "Scourge Of The Moon", "Restoration Of Our Legacy", "Battle Of New Worlds", "Fate Of Eternity", "Reincarnation Of The Faceless Ones", "Restoration Of The Universe", "Inspiration Of New Life", "Broken A New War", "Cultured By A Nuclear Winter", "Stranger To The Fog", "Experience In The Machines", "Right For The Aliens", "Created By The Aliens", "Hidden In The Aliens", "Broken The Truth", "Broken A Nuclear Winter", "Frozen By The Void", "Disguised By The Legends", "Changed By The Troopers", "Light Of Time Travellers", "Equality Of The End Of Earth", "Mystery Of A Nuclear Winter", "Longing For A Nuclear War", "Admiration For Droids", "Afraid Of A New War", "Inspired By The Eyes", "Hidden In The Guests", "Understanding Time Travel", "Abandoned On The Machines", "Disguised In My Android Servant", "Favor Of The Immortals", "Closed For Droids", "Father Of The Legends", "Light Of The New World", "Father Of The End", "Life After Time Travellers", "Abandoned By The Fog", "Abandoned In The Revolution", "Frozen By A Rise Of Machines", "Puzzle Of The Machines", "Alerted By The Galaxy", "Broken The New Planet", "Blindd By First Contact", "Joy Of Moon Rocks", "Longing For Droids", "Demand For Outer Space", "Joy Of The Intruders", "Equality In My Journey", "Colors Of Moondust", "Stranger To The Portal", "Intelligence In The New Age", "Life With The New Age", "Closed For The Ocean", "Experience In The Secrets Of The Ocean", "Married To Robots"};
		String[] filetypes = new String[] {".ai", ".eps", ".pdf", ".psd", ".jpg", ".gif", ".tif", ".png"};
		String[] cat_names = new String[] {"Traditional Art", "Literature", "Voice Acting", "3D", "Animation", "Film", "Modeling", "Photography", "Artisan Crafts", "Coding and Development", "Resources and Stock", "Game Design", "Digital Art"};
		List<String> tl = Arrays.asList(titles);
		ArrayList<String> tal = new ArrayList(tl);
		ArrayList<Integer[]> juncs = new ArrayList<Integer[]>();
		
		int count = 100;		
		while(!tal.isEmpty()) {
			String t_draw = tal.remove((int)(Math.random()*tal.size()));
			t_draw = t_draw.replaceAll("'", "''");
			String ft_draw = filetypes[(int)(Math.random()*filetypes.length)];
			int ar_draw = artists[(int)(Math.random()*artists.length)];
			int cat_draw = (int)(Math.random()*13);
			int cat_id = cat_draw + 100;
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			String ts = sdf.format(generateRandomTimestamp().getTime());
			String cat_lab = cat_names[cat_draw].replaceAll(" ", "").toLowerCase();
			User u = (User)UserDAO.getInstance().get((long)ar_draw);
			String url = "(" + t_draw.replaceAll(" ", "_") + ")" + "_" + cat_lab + "_" + ts ;
			
			int col_roll = (int)(Math.random() * 15);
			int col_cnt = 0;
			if(col_roll < 8) col_cnt = 1;
			else if (col_roll < 12) col_cnt = 2;
			else if (col_roll < 14) col_cnt = 3;
			else col_cnt = 4;
			
			long[] col_arts = new long[col_cnt];
			String[] cols = new String[col_cnt];
			col_arts[0] = u.getId();
			cols[0] = u.getUsername();
			int a = 1;
			while(a < col_cnt) {
				long ins = artists[(int)(Math.random()*artists.length)];
				if(searchList(col_arts, ins)) {
					continue;
				}else {
					col_arts[a] = ins;
					User col = (User)UserDAO.getInstance().get(ins);
					cols[a] = col.getUsername();
					a++;
				}
			}
			
			for(int i = 0; i < col_cnt; i++) {
				url += "_" + cols[i];
			}
			url +=  ft_draw;
			
			for(long i: col_arts) {
				if(i == 0) continue;
				
				Integer[] pair = new Integer[2];
				pair[0] = count;
				pair[1] = (int) i;
				juncs.add(pair);
			}
			
			try {
				myWriter.write("( " + count + ", '" + ft_draw + "', '" + ts + "', " + ar_draw + ", '" + t_draw + "', " + cat_id + ", '" + url + "'),\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			count++;
		}
		
		try {
			myWriter.write("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Integer[] ia: juncs) {
			try {
				myWriter.write("( " + ia[0] + ", " + ia[1] + ")" + ",\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		
		try {
			myWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static String generateReactionURL(Reaction r) {
		String url = "(" + r.getName() + ")_";

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH-mm-ss");
		
		url += sdf.format(r.getTimestamp()) + "_";
		
		if(r.getOwner() > 0) {
			User u = UserDAO.getInstance().get(r.getOwner());
			url += u.getUsername();
		}else {
			url += "public";
		}
		
		url += ".jpg";

		return url;
	}
	
	static boolean searchList(long[] values, long searchVal) {
		for(long i : values) {
			if (i == searchVal) {
				return true;
			}
		}
		return false;
	}
}

