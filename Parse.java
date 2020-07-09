import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;


import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

class Parse{
	
	// building a document from the XML file
    // returns a Document object after loading the book.xml file.
    public static Document getDocFromFile(String filename)
    throws ParserConfigurationException{
    {
       DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
       DocumentBuilder db = dbf.newDocumentBuilder();
       Document doc = null;
       
       try{
           doc = db.parse(filename);
       } catch (Exception ex){
           System.out.println("XML parse failure");
           ex.printStackTrace();
       }
       return doc;
    } // exception handling
    
    }  
    public static Scene[] readSceneData(Document d){
    	Scene[] Scenes = new Scene[40];
    	String desc = null, cardbudget, cardname;
    	int roleNum = 0, sceneNum = 0;
    	String[] roleNames = new String[3];
    	String[] roleLines = new String[3];
    	int[] roleLvls = new int[3];
    	int[] rolex = new int[3];
    	int[] roley = new int[3];
        Element root = d.getDocumentElement();
        
        NodeList cards = root.getElementsByTagName("card");
        
        for (int i=0; i < cards.getLength();i++){
            
            //reads data from the nodes
        	//need to read img num
            Node card= cards.item(i);
            cardname = card.getAttributes().getNamedItem("name").getNodeValue();
            cardbudget = card.getAttributes().getNamedItem("budget").getNodeValue();
            
            //reads data
                                         
            NodeList cChildren = card.getChildNodes();
            
            for (int j=0; j< cChildren.getLength(); j++){
                
              Node sub = cChildren.item(j);
            
              if("scene".equals(sub.getNodeName())){
                 desc = ((NodeList) sub).item(0).getTextContent();
              }
              //need to read area
              else if("part".equals(sub.getNodeName())){
            	  roleLvls[roleNum] = Integer.parseInt(sub.getAttributes().getNamedItem("level").getNodeValue());
            	  roleNames[roleNum] = sub.getAttributes().getNamedItem("name").getNodeValue();
            	  roleLines[roleNum] = sub.getChildNodes().item(3).getTextContent();
            	  rolex[roleNum] = Integer.parseInt(sub.getChildNodes().item(1).getAttributes().getNamedItem("x").getNodeValue());
            	  roley[roleNum] = Integer.parseInt(sub.getChildNodes().item(1).getAttributes().getNamedItem("y").getNodeValue());
            	  roleNum++;
              }
           }
           Scenes[sceneNum] = new Scene(Integer.parseInt(cardbudget), roleNum, roleNames, roleLines, roleLvls, rolex, roley, cardname, desc);
           sceneNum++;
           roleNum = 0;
        }
        return Scenes;
    
    }

    
    // reads data from XML file and prints data
    public static Room[] readRoomData(Document d){
      Room[] Rooms= new Room[12];
      String roomname, takes = null;
  	  int roleNum = 0, neighNum = 0, x = 0, y = 0, number;
  	  String[] roleNames = new String[4];
      String[] roleLines = new String[4];
      String[] neighbors = new String[4];
      int[] takex = new int[3];
      int[] takey = new int[3];
  	  int[] roleLvls = new int[4];
  	  int[] rolex = new int[4];
  	  int[] roley = new int[4];
      Element root = d.getDocumentElement();
      
      NodeList rooms= root.getElementsByTagName("set");
      
      for (int i=0; i < rooms.getLength();i++){
          //reads data from the nodes
          Node room = rooms.item(i);
          roomname= room.getAttributes().getNamedItem("name").getNodeValue();
          
          //reads data
                                       
          NodeList rChildren = room.getChildNodes();
          
          for (int j=0; j < rChildren.getLength(); j++){
           
            Node sub = rChildren.item(j);
            if("neighbors".equals(sub.getNodeName())){
               NodeList nChildren = sub.getChildNodes();
               for (int k = 0 ; k < nChildren.getLength() ; k++){
                Node neigh = nChildren.item(k);
                if("neighbor".equals(neigh.getNodeName())){
                   neighbors[neighNum] = neigh.getAttributes().getNamedItem("name").getNodeValue();
                   neighNum++;
                }
               }
            } else if("area".equals(sub.getNodeName())) {
            	x = Integer.parseInt(sub.getAttributes().getNamedItem("x").getNodeValue());
            	y = Integer.parseInt(sub.getAttributes().getNamedItem("y").getNodeValue());
            } else if ("takes".equals(sub.getNodeName())){
            	NodeList tChildren = sub.getChildNodes();
            	takes = tChildren.item(1).getAttributes().getNamedItem("number").getNodeValue();
            	for (int t = 0 ; t < tChildren.getLength() ; t++) {
            		Node take = tChildren.item(t);
            		if("take".equals(take.getNodeName())) {
            			number = Integer.parseInt(take.getAttributes().getNamedItem("number").getNodeValue())-1;
            			takex[number] = Integer.parseInt(take.getChildNodes().item(0).getAttributes().getNamedItem("x").getNodeValue());
            			takey[number] = Integer.parseInt(take.getChildNodes().item(0).getAttributes().getNamedItem("y").getNodeValue());
            		}
            	}
            }
            else if("parts".equals(sub.getNodeName())){
          	  NodeList pChildren = sub.getChildNodes();
          	  for(int k = 0; k < pChildren.getLength() ; k++){
                  Node psub = pChildren.item(k);
          		  if("part".equals(psub.getNodeName())){
          			  roleLvls[roleNum] = Integer.parseInt(psub.getAttributes().getNamedItem("level").getNodeValue());
          			  roleNames[roleNum] = psub.getAttributes().getNamedItem("name").getNodeValue();
          			  roleLines[roleNum] = psub.getChildNodes().item(3).getTextContent();
          			  rolex[roleNum] = Integer.parseInt(psub.getChildNodes().item(1).getAttributes().getNamedItem("x").getNodeValue());
          			  roley[roleNum] = Integer.parseInt(psub.getChildNodes().item(1).getAttributes().getNamedItem("y").getNodeValue());
          			  roleNum++;
          		  }
          	  }
            }
            
         }
          Rooms[i+1] = new Room(Integer.parseInt(takes), roomname, roleNum, roleNames, roleLvls, roleLines, rolex, roley, neighbors, neighNum, x, y, takex, takey, i);
          roleNum = 0;
          neighNum = 0;
      }
      NodeList trailerLS = root.getElementsByTagName("trailer");
      for (int i=0; i<trailerLS.getLength();i++){
          NodeList tChildren = trailerLS.item(i).getChildNodes();
          for (int j=0; j < tChildren.getLength(); j++){
            Node sub = tChildren.item(j);
            if("neighbors".equals(sub.getNodeName())){
               NodeList nChildren = sub.getChildNodes();
               for (int k = 0 ; k < nChildren.getLength() ; k++){
                Node neigh = nChildren.item(k);
                if("neighbor".equals(neigh.getNodeName())){
                   neighbors[neighNum] = neigh.getAttributes().getNamedItem("name").getNodeValue();
                   neighNum++;
                }
               }
            } else if("area".equals(sub.getNodeName())) {
            	x = Integer.parseInt(sub.getAttributes().getNamedItem("x").getNodeValue());
            	y = Integer.parseInt(sub.getAttributes().getNamedItem("y").getNodeValue());
            }
          }
          Rooms[0] = new Room("trailer", neighbors, neighNum, x, y);
          neighNum = 0;
      }
      NodeList officeLS = root.getElementsByTagName("office");
      for (int i=0; i<officeLS.getLength();i++){
          NodeList oChildren = officeLS.item(i).getChildNodes();
          for (int j=0; j < oChildren.getLength(); j++){
            Node sub = oChildren.item(j);
            if("neighbors".equals(sub.getNodeName())){
               NodeList nChildren = sub.getChildNodes();
               for (int k = 0 ; k < nChildren.getLength() ; k++){
                Node neigh = nChildren.item(k);
                if("neighbor".equals(neigh.getNodeName())){
                   neighbors[neighNum] = neigh.getAttributes().getNamedItem("name").getNodeValue();
                   neighNum++;
                }  
               }
            } else if("area".equals(sub.getNodeName())) {
            	x = Integer.parseInt(sub.getAttributes().getNamedItem("x").getNodeValue());
            	y = Integer.parseInt(sub.getAttributes().getNamedItem("y").getNodeValue());
            }
          }
          Rooms[11] = new Room("office", neighbors, neighNum, x, y);
      }
      return Rooms;
    }
}


