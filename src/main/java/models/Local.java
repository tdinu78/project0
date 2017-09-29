package models;

public class Local {
        private int id;
        private String displayName;
        private String name;

        public Local() {}

        public Local(int id, String displayName, String name) {
            this.id = id;
            this.displayName = displayName;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
}
