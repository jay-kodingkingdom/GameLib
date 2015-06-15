package GameLib.archi;

import java.util.Set;

public interface Rule {
	public Set<Action> getActions(State state);
}
