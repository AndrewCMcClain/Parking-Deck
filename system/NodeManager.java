package system;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

public class NodeManager {
	private static NodeManager nm;
	private static Object lockObject;
	private Map<UUID, Node> nodes = new TreeMap<UUID, Node>();
	
	protected static NodeManager getNodeManager(){
		if(nm == null){
			synchronized(lockObject){
				if(nm == null){
					nm = new NodeManager();
				}
			}
		}
		return nm;
	}
	
	private NodeManager(){
		
	}
	
	protected void registerNode(Node n){
		nodes.put(n.getIdentifier(), n);
	}
	
	protected void deRegisterNode(Node n){
		nodes.remove(n.getIdentifier());
	}
	
	protected Node getNode(UUID id){
		return nodes.get(id);
	}
	
	protected Node[] getNodes(){
		return (Node[]) nodes.values().toArray();
	}
	
	protected UUID[] getNodeIds(){
		return (UUID[]) nodes.keySet().toArray();
	}
}
