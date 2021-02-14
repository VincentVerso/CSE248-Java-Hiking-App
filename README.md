# CSE248-Java-Hiking-App
Admin login
Username: vin
Password: pass1234

User login
Username: jim
Password: pass1234

For my UserDatabase class I used a TreeMap<String, Account> which can hold both UserAccount and AdminAccount instances. The reason for using the data structure is because it can easily support as man users as needed while still be fast and efficient for fetching a user on login. 

As for my TrailsDatabase class, I used a HashMap<int, Trail>. This is because I know the max number of trail I want to the data structure to support. I used an integer as the key keeping track and incrementing by one each time a trail is added. This is because trails could share an address or name, thus making the key unique.

Within my Account class I used a TreeMap<LocalDate, LinkedList<TrailEntry>> trailHistory. With this structure a LocalDate object can be mapped to list that contains the history for the given day. Byy nature of how its setup the history will alwas be in order of date and time. Since we dont know how many trails a user may take in one day a linked list was used to accommodate this. UserAccount and AdminAccount classes are sub classes of Account.java, inheriting its properties.
