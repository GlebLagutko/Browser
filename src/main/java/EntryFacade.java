public class EntryFacade {
    private static EntryFacade facade;

    public static synchronized EntryFacade getInstance() {
        if (facade == null) {
            facade = new EntryFacade();
        }
        return facade;
    }

    public DepartmentFacade getDepatmentFacade(){
        return new DepartmentFacade();
    }

    public UserFacade getUserFacade(){
        return new UserFacade();
    }

}
