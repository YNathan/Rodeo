package Entity;

/**
 * 
 * @author Yaacov
 *
 */
public class Gelt {
	private int nDebterID = -1;
	private int nAmount = -1;
	private int nEntitledID = -1;

	public Gelt(int nDebterID, int nAmount, int nEntitledID) {
		super();
		this.nDebterID = nDebterID;
		this.nAmount = nAmount;
		this.nEntitledID = nEntitledID;
	}

	public Gelt() {
	}

	public int getDebterID() {
		return nDebterID;
	}

	public int getAmount() {
		return this.nAmount;
	}

	public int getEntitledID() {
		return nEntitledID;
	}

	public void setnDebterID(int nDebterID) {
		this.nDebterID = nDebterID;
	}

	public void setnAmount(int nAmount) {
		this.nAmount = nAmount;
	}

	public void setnEntitledID(int nEntitledID) {
		this.nEntitledID = nEntitledID;
	}

	@Override
	public String toString() {
		return "Gelt [DebterID=" + nDebterID + ", Amount=" + nAmount + ", EntitledID=" + nEntitledID + "]";
	}

}
