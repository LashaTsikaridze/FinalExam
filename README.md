✨ ＦＥＡＴＵＲＥＳ

🔐 User Authentication
Securely register, log in, and log out using Firebase Authentication for a seamless user experience.

👤 User Profile Management
Easily view and update user details—including First Name, Last Name, Age, Gender, and Email—synchronized with Firebase Realtime Database.

🐶 Local Animal Database
Add, browse, and delete animal records locally using SQLite, with support for images, names, ages, and origins.

🧭 Intuitive Navigation
Effortlessly switch between Home, Add Animal, and Profile screens using BottomNavigationView integrated with Navigation Components.

🎨 Modern and Responsive UI
Designed with ViewBinding and RecyclerView for smooth performance. Features customizable fonts, dynamic text sizes, and styles such as bolded names, italicized biographies, and color-coded section headers for enhanced readability.



𝐇𝐎𝐌𝐄 𝐒𝐂𝐑𝐄𝐄𝐍 – 𝐀𝐍𝐈𝐌𝐀𝐋𝐒 𝐋𝐈𝐒𝐓 🐾
At the top of the screen, you can see the title:
🗂️ Animals List

📜 Below the title, there's a list of animals (e.g., 🦡 Badger, 🦦 Otter) each displayed with:

📸 Image

⚖️ Weight

🎉 Age

🌏 Origin

𝐄𝐚𝐜𝐡 𝐚𝐧𝐢𝐦𝐚𝐥 𝐚𝐥𝐬𝐨 𝐢𝐧𝐜𝐥𝐮𝐝𝐞𝐬: 🐾

📖 A short description about the species

🌍 A translated biography in English (italicized for emphasis)

At the bottom, there's a smooth and intuitive navigation bar:

🏰 Home (Directory of animals)

➕ Add (Add a new animal)

🧙 Profile (User profile section)


![homeememe](https://github.com/user-attachments/assets/903ff878-04e9-4606-b1dd-a8dca5a48325)





➕ 𝐀𝐃𝐃 𝐀𝐍𝐈𝐌𝐀𝐋 𝐒𝐂𝐑𝐄𝐄𝐍 🐾
This screen allows users to add a new animal to the local database 🐾 using a simple and clean form interface.

🗂️ Form Fields:

🔖 Name – Enter the animal’s full name
⚗️ Weight (kg) – Specify the weight in kilograms
🗺️ Origin (Country) – Provide the country or place of origin
📆 Age – Indicate the age of the animal
🖼️ Image URI – Link or path to the animal’s image

📥 Once all fields are filled, the user can tap the "დამატება" (Add) button to insert the new animal into the local SQLite database.

🔽 Navigation Bar:

📚 Home – View existing animal entries
✍️ Add – You are currently here
🧑‍💻 Profile – See user account information

✨ The form uses simple text fields for input and a modern button design to enhance usability. All labels are written in Georgian, supporting multilingual UI.

![dddaadd](https://github.com/user-attachments/assets/36c26d91-cdc3-43f5-bd29-7f353e7264a1)





👤 𝐏𝐑𝐎𝐅𝐈𝐋𝐄 𝐒𝐂𝐑𝐄𝐄𝐍 – 𝐔𝐒𝐄𝐑 𝐈𝐍𝐅𝐎𝐑𝐌𝐀𝐓𝐈𝐎𝐍 🔐
This screen displays the authenticated user’s profile information retrieved from Firebase Realtime Database 🔐.

📧 At the top, the user’s email address is shown in bold.
Beneath that, the following personal details are listed:

🧙‍♂️ First Name: Lasha

🧝‍♂️ Last Name: Tsikaridze

🦉 Age: 19

⚧ Gender: Male

🔒 A Sign out button is located at the bottom, allowing the user to safely log out from their account.

🧭 The bottom navigation bar allows you to easily switch between:

🏛️ Home – View animal list

🧬 Add – Add a new animal

🧙‍♀️ Profile – You are here

🎴 This screen is styled with a clean card layout and uses readable fonts to display user data in a clear and organized manner.

![profilee](https://github.com/user-attachments/assets/892677f6-4588-4076-b3dc-66e33567cce3)

🚀 𝗚𝗘𝗧𝗧𝗜𝗡𝗚 𝗦𝗧𝗔𝗥𝗧𝗘𝗗
✅ Prerequisites
Android Studio Bumblebee or later

A Firebase project:

Enable Email/Password Authentication

Enable Realtime Database

Add your google-services.json file to the /app directory

🔧 Installation
Clone the repository:

bash
Copy
Edit
git clone https://github.com/yourusername/finalexam.git
Open the project in Android Studio

Add google-services.json to /app folder

Sync Gradle and run the app on emulator or device


✨ 𝐀𝐔𝐓𝐇𝐎𝐑 / 𝐂𝐎𝐍𝐓𝐀𝐂𝐓 

Lasha Tsikaridze

📧 Email: tsikaridzelashaa@gmail.com

🔗 LinkedIn: linkedin.com/in/lashatsikaridze 
