package com.example.fitools.shenyue;

public class AppUtil {
    public static final class SharingServer {
        public static final String HOST = "192.168.150.2";//192.168.1.125
        public static final int PORT = 8088;
        public static final String COMMAND_SET_LOCATION = "set_location";
        public static final String REQUEST_MEMBER_LOCATION = "request_location";
        public static final String REQUEST_STOP = "over";
        public static final String COMMAND_SET_USERID = "userId";
        public static final String COMMAND_SET_GROUPID = "groupId";
        public static final String SEPARATOR_LOCATION_DIFFER_MEMBER = "#";
        public static final String SEPARATOR_LOCATION_LAT_LON = ",";
        public static final String SEPARATOR_LOCATION_ID_LOC = "->";
        public static final String SEPARATOR_COMMAND_CONTENT = ":";
        public static final String RECEIVED_MEMBER_LOCATIONS = "member_locations";
    }

    public static final class JFinalServer {
        public static final String HOST = "192.168.23.1";//192.168.1.125
        public static final int PORT = 8080;
        public static final String xyurl = "http://"+ JFinalServer.HOST+":"+ JFinalServer.PORT+ "/user/login";
    }

    public static final class User {
        public static String USER_ID = "";
        public static String USER_NAME = "";
        public static String USER_IMG = "";
        public static String USER_GENDER = "";

    }

    public static final class Group {
        public static String GROUP_ID = "";
        public static String CHAT_TEAM_ID="";
        public static String GROUP_NAME = "";
        public static String GROUP_CAPTIAN = "";
    }
}
