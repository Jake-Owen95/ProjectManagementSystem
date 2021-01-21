# Import file with functions

from functions import *

# Set variables for task manager

close = False
login = False
user_names = ""
user_passwords = ""
logged_in = False

# Log in section

while close == False:
    while logged_in == False:
        users = open("user.txt", "r")
        user_names = []
        user_passwords = []
        for line in users:
            temp = line.strip()
            temp = temp.split(", ")
            user_names.append(temp[0])
            user_passwords.append(temp[0])
        users.close()

        # Give user choice to login or exit

        choice = input("Type l to login or e to exit: ")
        if choice == "l":
            while logged_in == False:
                username = input("Enter user name: ")
                password = input("Enter your password: ")
                if username not in user_names:
                    print("\nUser name does not exist")
                elif password not in user_passwords:
                    print("Incorrect password")
                else:
                    logged_in = True
        elif choice == "e":
            close = True
            break

    # When user is logged in

    while logged_in == True:
        print("\n Please choose one of the following:")
        if username == "admin":
            print("r - Register user")
        print("a - Add Task")
        print("va - View All Tasks")
        print("vm - View My tasks")
        if username  == "admin":
            print("ds - Display Statistics")
            print("gr - Generate Reports")
        print("b - Back to login")
        option = input(": ")
        if option == "r" and username == "admin":
            reg_user(user_names)
        if option == "a":
            add_task(user_names)
        if option == "va":
            view_all()
        if option == "vm":
            view_mine(username,user_names)
        if option =="ds" and username == "admin":
            show_stats()
        if option == "gr" and username == "admin":
            generate_report()
        if option == "b":
            logged_in = False

