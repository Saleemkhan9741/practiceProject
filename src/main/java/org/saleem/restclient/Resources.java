package org.saleem.restclient;

public enum Resources{

        GET_USERS("/api/users?page=2");
        private String resource;
        Resources(String s) {
            this.resource = s;
        }

        public String getResourceUrl(){
            return resource;
        }
}
