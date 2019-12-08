package com.inti.student.result;

public class Result  {
    private String operatingSystem;
    private String dataInfo;
    private String androidS;
    private String programPython;
    private String realWP;
    private String softwareE;
    private String socialLegal;
    private String totalResult;

    public Result(){


    }

    public Result(String operatingSystem, String dataInfo, String androidS, String programPython,String realWP,String softwareE,String socialLegal,String totalResult) {
        this.operatingSystem = operatingSystem;
        this.dataInfo = dataInfo;
        this.androidS = androidS;
        this.programPython = programPython;
        this.realWP =realWP;
        this.softwareE =softwareE;
        this.socialLegal =socialLegal;
        this.totalResult = totalResult;

    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem){
        this.operatingSystem=operatingSystem;
    }
    public String getDataInfo(){
        return dataInfo;
    }
    public void setDataInfo(String dataInfo){
        this.dataInfo=dataInfo;
    }

    public String getAndroidS() {
        return androidS;
    }
    public void setAndroidS(String androidS){
        this.androidS = androidS;
    }

    public String getProgramPython(){
        return programPython;
    }
    public void setProgramPython(String programPython){
        this.programPython=programPython;
    }

    public String getSoftwareE() {
        return softwareE;
    }

    public void setSoftwareE(String softwareE){
        this.softwareE=softwareE;
    }
    public String getSocialLegal(){
        return socialLegal;
    }
    public void setSocialLegal(String socialLegal){
        this.socialLegal=socialLegal;
    }

    public String getRealWP(){
        return realWP;
    }

    public void setRealWP(String realWP){
        this.realWP=realWP;
    }

    public String getTotalResult(){
        return totalResult;
    }

    public void setTotalResult(String totalResult){
        this.totalResult=totalResult;
    }

}
