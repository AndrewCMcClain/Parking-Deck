package system;

import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

public class LinkManager {
	private static LinkManager lm;
	private static Object lockObject;
	private Map<UUID, Link> links = new TreeMap<UUID, Link>();
	
	protected static LinkManager getLinkManager(){
		if(lm == null){
			synchronized(lockObject){
				if(lm == null){
					lm = new LinkManager();
				}
			}
		}
		return lm;
	}
	
	private LinkManager(){
		
	}
	
	protected void registerLink(Link n){
		links.put(n.getIdentifier(), n);
	}
	
	protected void deRegisterLink(Link n){
		links.remove(n.getIdentifier());
	}
	
	protected Link getLink(UUID id){
		return links.get(id);
	}
	
	protected Link[] getLinks(){
		return (Link[]) links.values().toArray();
	}
	
	protected UUID[] getLinkIds(){
		return (UUID[]) links.keySet().toArray();
	}
}
