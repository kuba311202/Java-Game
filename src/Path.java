public class Path{
    protected int pathNumber;
    protected String pathType;
    protected String whatToInitialize;
    protected int[] pathPlaceNumber;
    protected int[] pathAct;
    public Path(int pathNumber){
        if (pathNumber==1){
            this.pathNumber=pathNumber;
            this.pathType = "Fight";
            this.whatToInitialize = "Fight1";
        }
    }
}
