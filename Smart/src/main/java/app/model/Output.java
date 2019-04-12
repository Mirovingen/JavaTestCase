package app.model;

public class Output {
    private String ssoid,grp,type,subtype,url,orgid,formid,code,ltpa,sudirresponse,ymdh;
    private int ts;
    private Output(builder builder){
        this.ssoid			= builder.ssoid;
        this.ts				= builder.ts;
        this.grp			= builder.grp;
        this.type			= builder.type;
        this.subtype		= builder.subtype;
        this.url			= builder.url;
        this.orgid			= builder.orgid;
        this.formid			= builder.formid;
        this.code			= builder.code;
        this.ltpa			= builder.ltpa;
        this.sudirresponse	= builder.sudirresponse;
        this.ymdh			= builder.ymdh;
    }

    public  String getSsoid() {
        return ssoid;
    }

    public  String getGrp() {
        return grp;
    }

    public  String getType() {
        return type;
    }

    public  String getSubtype() {
        return subtype;
    }

    public  String getUrl() {
        return url;
    }

    public  String getOrgid() {
        return orgid;
    }

    public  String getFormid() {
        return formid;
    }

    public  String getCode() {
        return code;
    }

    public  String getLtpa() {
        return ltpa;
    }

    public  String getSudirresponse() {
        return sudirresponse;
    }

    public  String getYmdh() {
        return ymdh;
    }

    public int getTs() {
        return ts;
    }

    public static class builder
    {
        private String ssoid,grp,type,subtype,url,orgid,formid,code,ltpa,sudirresponse,ymdh;
        private int ts;
        public builder ssoid(String ssoid) {
            this.ssoid = ssoid;
            return this;
        }
        public builder ts(int ts) {
            this.ts = ts;
            return this;
        }
        public builder grp(String grp) {
            this.grp = grp;
            return this;
        }
        public builder type(String type) {
            this.type = type;
            return this;
        }
        public builder subtype(String subtype) {
            this.subtype = subtype;
            return this;
        }
        public builder url(String url) {
            this.url = url;
            return this;
        }
        public builder orgid(String orgid) {
            this.orgid = orgid;
            return this;
        }
        public builder formid(String formid) {
            this.formid = formid;
            return this;
        }
        public builder code(String code) {
            this.code = code;
            return this;
        }
        public builder ltpa(String ltpa) {
            this.ltpa = ltpa;
            return this;
        }
        public builder sudirresponse(String sudirresponse) {
            this.sudirresponse = sudirresponse;
            return this;
        }
        public builder ymdh(String ymdh) {
            this.ymdh = ymdh;
            return this;
        }
        public Output build(){
            return new Output(this);
        }
    }

}
