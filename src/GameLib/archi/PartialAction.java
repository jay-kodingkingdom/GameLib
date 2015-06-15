package GameLib.archi;

public interface PartialAction {
	public Action combineActions(PartialAction... actions);
}
