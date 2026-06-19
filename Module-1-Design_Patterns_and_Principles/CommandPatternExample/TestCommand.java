package CommandPatternExample;

public class TestCommand {
    public static void main(String[] args) {
        
        Light livingRoomLight = new Light("Living Room");
        Light kitchenLight = new Light("Kitchen");
        
        Command livingRoomOn = new LightOnCommand(livingRoomLight);
        Command livingRoomOff = new LightOffCommand(livingRoomLight);
        Command kitchenOn = new LightOnCommand(kitchenLight);
        Command kitchenOff = new LightOffCommand(kitchenLight);
        
        RemoteControl remote = new RemoteControl();
        
        System.out.println("Controlling Living Room Light");
        remote.setCommand(livingRoomOn);
        remote.pressButton();
        remote.setCommand(livingRoomOff);
        remote.pressButton();
        remote.pressUndo();  
        remote.pressRedo();  
        
        System.out.println("\nControlling Kitchen Light");
        remote.setCommand(kitchenOn);
        remote.pressButton();
        remote.setCommand(kitchenOff);
        remote.pressButton();
        
        System.out.println("\nUndo/Redo Sequence");
        remote.pressUndo();  
        remote.pressUndo();  
        remote.pressRedo();  
    }
}
