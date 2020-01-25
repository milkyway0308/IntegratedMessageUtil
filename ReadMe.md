# *IntegratedMessageUtil*
IntegratedMessageUtil is integrated message/placeholder manager for minecraft server plugins.

## Installation
  #### Without build tools
  1. Download last release of IntegratedMessageUtil from git release history.
  2. Add to dependency. 
  3. Add IntegratedMessageUtil to softdepend.
  4. Install IntegratedMessageUtil to your server plugin folder.
  
  IntegratedMessageUtil is standalone plugin. 
  
  You have to install plugin to your server.
  #### With Gradle
  > Preparing.
  #### With Maven
  > Preparing.
  
 ## Example
   ##### 1.0
   ```java
   public class MainClass extends JavaPlugin {
      
    @Override
    public void onLoad(){
        // <> Placeholder registration
        PlaceHolderStorage phs = IntegratedMessageUtil.getHolderStorage('<','>');
        // <testHolder> placeholder registered
        phs.registerPlaceHolder("testHolder",new TestPlayerNameHolder());
        // <testHolder2> placeholder registered
        phs.registerPlaceHolder("testHolder2",new TestHelloWorldHolder());
    }
    
    @Override
    public void onEnable(){
        // Sentence registration.
        // This step is not required if you not used sentence storage.
        File file = new File(getDataFolder(), "message.yml");
        // Load sentence from file
        PreparsedSentenceStorage storage = IntegratedMessageUtil.loadSentenceStorage("TestPlugin", file);
        // There's two way to register sentence - PreparsedDataSentence and String.
        // They work same, but you can pick anything you want.
        storage.addSentenceIfnotExists("HELLO_WORLD", "Hello, <testHolder2>!");
        storage.addSentenceIfnotExists("HELLO_PLAYER", new PreparsedDataSentence("Hello, <testHolder>!"));
        if(storage.isRequireSave())
            storage.writeToFile(file);
        // Get some sentence
        PreparsedDataSentence sent = IntegratedMessageUtil.getSentence("TestPlugin","HELLO_WORLD");
        // And print
        Bukkit.getConsoleSender().sendMessage(
                sent.toString(
                        // You need to input Player object to print player place holder
                        ParameterWrapper.of()
                )
        );
    }
    
    public class TestPlayerNameHolder implements IPlaceHolder {
        @Override
        public String replaceHolder(ParameterWrapper param) {
            // Parameter replacement.
            // If parameter not contains player,
            // You have to return original placeholder cause it can't parse.
            if(param.getParameter(Player.class) == null)
                return "<testHolder>";
             return param.getParameter(Player.class).getName();
        }
        
         @Override
         public IPlaceHolder getWrappedHolder(String s) {
            // You have to return instance of placeholder in this method.
            // Parameter s contains content of placeholder.
            // If you used like this, <testHolder:15>
            // Parameter s will be "testHolder:15".
            return new TestPlayerNameHolder();
         }
    }
    
        
    public class TestHelloWorldHolder implements IPlaceHolder {
        @Override
        public String replaceHolder(ParameterWrapper param) {
             return "World";
        }
        
         @Override
         public IPlaceHolder getWrappedHolder(String s) {
            // You have to return instance of placeholder in this method.
            // Parameter s contains content of placeholder.
            // If you used like this, <testHolder2:15>
            // Parameter s will be "testHolder2:15".
            return new TestHelloWorldHolder();
         }
    }
   }
   ```