package com.example.app_tema1_fredpor;

import androidx.annotation.NonNull;

public class Usuario {
        private String userName;
        private String userPass;
        private String userSex;

        public Usuario(String userName, String userPass, String userSex ){
             this.userName = userName;
             this.userPass = userPass;
             this.userSex = userSex;
        }

        public String getUserName() {
                return userName;
        }
        public String getUserPass() {return userPass;}

        public String getUserSex() {
                return userSex;
        }

        @NonNull
        @Override
        public String toString() {
                return "Usuario: " + userName + " Contraseña: " +userPass;
        }
}

