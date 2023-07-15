package org.saleem.enums;

public enum Resources{

        GET_USERS("/api/users?page=2"),
        CREATE_USER("/api/users");

        private String resource;
        Resources(String s) {
            this.resource = s;
        }

        public String getResourceUrl(){
            return resource;
        }
}
