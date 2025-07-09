✨ Features
🔐 User Authentication
Register, log in, and log out using Firebase Authentication.

👤 User Profile Management
View and edit user details (First name, Last name, Age, Gender, Email) fetched from Firebase Realtime Database.

🐶 Local Animal Database
Add, view, and delete animal entries using SQLite, with support for image, name, age, and origin.

🧭 Smooth Navigation
Switch between Home, Add Animal, and Profile tabs using BottomNavigationView and Navigation Components.

🎨 Modern UI
Built with ViewBinding and RecyclerView for efficient UI rendering. Supports custom fonts, varying text sizes and styles for different elements (e.g., bolded names, italic bios, colored section headers).







 Home Screen – Animals List
At the top of the screen, you can see the title:
📝 Animals List

📋 Below the title, there's a list of animals (e.g., 🐶 Dog, 🐱 Cat) each displayed with:

📷 Image

⚖️ Weight

🎂 Age

🌍 Origin

Each animal also includes:

🧾 A short description about the species

🌐 A translated biography in English (italicized for emphasis)

At the bottom, there's a smooth and intuitive navigation bar:

🏠 Home (Directory of animals)

➕ Add (Add a new animal)

👤 Profile (User profile section)
![homeememe](https://github.com/user-attachments/assets/903ff878-04e9-4606-b1dd-a8dca5a48325)




➕ Add Animal Screen
This screen allows users to add a new animal to the local database 🐾 using a simple and clean form interface.

📝 Form Fields:

🐾 Name – The name of the animal

⚖️ Weight (kg) – The weight in kilograms

🌍 Origin (Country) – The country or place of origin

🐣 Age – The age of the animal

📸 Image URI – Path or URI to the animal’s image

💾 After filling out the fields, the user can press the "დამატება" (Add) button to save the animal to the local database.

🧭 The bottom navigation bar allows switching to:

🏠 Home – View list of animals

➕ Add – You are here

👤 Profile – View user profile

✨ The form uses simple text fields for input and a modern button design to enhance usability. All labels are written in Georgian, supporting multilingual UI.
![dddaadd](https://github.com/user-attachments/assets/36c26d91-cdc3-43f5-bd29-7f353e7264a1)




👤 Profile Screen – User Information
This screen displays the authenticated user’s profile information retrieved from Firebase Realtime Database 🔐.

📧 At the top, the user’s email address is shown in bold.
Beneath that, the following personal details are listed:

🙍‍♂️ First Name: Lasha

🧑‍💼 Last Name: Tsikaridze

🎂 Age: 19

🚻 Gender: Male

🔵 A Sign out button is located at the bottom, allowing the user to safely log out from their account.

🧭 The bottom navigation bar allows you to easily switch between:

🏠 Home – View animal list

➕ Add – Add a new animal

👤 Profile – You are here

🎨 This screen is styled with a clean card layout and uses readable fonts to display user data in a clear and organized manner.
![profilee](https://github.com/user-attachments/assets/892677f6-4588-4076-b3dc-66e33567cce3)
