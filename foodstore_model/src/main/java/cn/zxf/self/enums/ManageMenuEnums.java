package cn.zxf.self.enums;

public class ManageMenuEnums{
    public enum CLIENT_ID {

        MAIN_SYS(1001L),
        USER_SYS(1002L),
        ADMIN_SYS(1003L);

        private Long client_id;

        CLIENT_ID(Long client_id){
            this.client_id = client_id;
        }

        public Long get() {
            return this.client_id;
        }

    }


    public enum IS_DELETE{

        /**
         * 正常
         */
        NORMAL(0),
        /**
         * 删除
         */
        DELETE(1);

        private int type ;

        IS_DELETE(int type) {
            this.type = type;
        }

        public int get() {
            return type;
        }
    }

}
