package com.saviorru.comsserver.model;

    public class Meet {
        private Player firstPlayer;
        private Player secondPlayer;
        private Boolean assigned;

        public Boolean isAssigned() {
            return assigned;
        }

        public Meet(Player firstPlayer, Player secondPlayer) throws Exception {
            if ((firstPlayer == null) || (secondPlayer == null))
                throw new NullPointerException();
            if (firstPlayer.equals(secondPlayer))
                throw new Exception("Duplicate players in one meet is not allowed");
            this.firstPlayer = firstPlayer;
            this.secondPlayer = secondPlayer;
            this.assigned = false;
        }

        public Player getFirstPlayer() {
            return this.firstPlayer;
        }

        public Player getSecondPlayer() {
            return this.secondPlayer;
        }

        public void assign() throws Exception {
            if (this.isAssigned())
                throw new Exception("Meet is already assigned");
            this.assigned = true;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Meet meet = (Meet) o;

            if (!getFirstPlayer().equals(meet.getFirstPlayer())) return false;
            return getSecondPlayer().equals(meet.getSecondPlayer());
        }
    }

