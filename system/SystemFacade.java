package system;

import java.util.Collection;
import java.util.UUID;

public class SystemFacade {
	public static Node getNode(UUID nodeID){
		return NodeManager.getNodeManager().getNode(nodeID);
	}
	
	public static Node[] getNodes(){
		return NodeManager.getNodeManager().getNodes();
	}
	
	public static UUID[] getNodeIds(){
		return NodeManager.getNodeManager().getNodeIds();
	}
	
	public static Link getLink(UUID linkID){
		return LinkManager.getLinkManager().getLink(linkID);
	}
	
	public static Link[] getLinks(){
		return LinkManager.getLinkManager().getLinks();
	}
	
	public static UUID[] getLinkIds(){
		return LinkManager.getLinkManager().getLinkIds();
	}
}
