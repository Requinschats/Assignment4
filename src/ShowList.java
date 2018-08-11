import java.util.NoSuchElementException;

public class ShowList {
	// b)
	private ShowNode head;		
	// c)
	private int size;
	
	private int numIterations;
	
	public int getNumIterations() {
		return numIterations;
	}

	public int getSize() {
		return size;
	}

	// d) default constructor
	public ShowList() {
		head = null;
	}

	// TODO e) copy constructor RECHECK 
	public ShowList(ShowList obj) {
		this.head = obj.head;
		this.size = obj.size;
		ShowNode newCurrent = this.head;
		ShowNode oldCurrent = obj.head;
		
		int count = 0;
		while (count < this.size - 1) {							// -1 because head is already copied
			newCurrent.pointer = new ShowNode(oldCurrent.pointer);
			newCurrent = newCurrent.pointer;
			oldCurrent = oldCurrent.pointer;
			count++;
		}
		
	}

	// f) addToStart method
	public void addToStart(TVShow obj) {
		head = new ShowNode(obj, head);
	}

	// g) insertAtIndex method
	public void insertAtIndex(TVShow obj, int i) {
		if (i < 0 || i > (size - 1)) {
			throw new NoSuchElementException("There is no such index"); 		//TODO a reviser
		}
		else if (i == 0) {
			ShowNode newNode = new ShowNode(obj, null);
			newNode.pointer = head;
			head = newNode;
			size++;
		}
		else {
			ShowNode newNode = new ShowNode(obj, null);
			ShowNode previous = head;
			int count = 0;
			while (count < i - 1) {
				previous = previous.pointer;
				count++;
			}
			ShowNode current = previous.pointer;
			newNode.pointer = current;
			previous.pointer = newNode;
			size++;
		}	
	}
	
	// h) deleteFromIndex method
	public void deleteFromIndex(int i) {
		if (i < 0 || i > (size - 1)) {
			throw new NoSuchElementException("There is no such index"); 		//TODO a reviser
		}
		else if(i == 0) {
			deleteFromStart();
		}
		else {
			ShowNode previous = head;
			int count = 0;
			while (count < (i - 1)) {
				previous = previous.pointer;
				count++;
			}
			ShowNode current = previous.pointer;
			previous.pointer = current.pointer;
			current.pointer = null;
			size--;
		}
	}
	
	// i) deleteFromStart method
	public void deleteFromStart() {		//TODO what are the special cases here????
		ShowNode temp = head;
		head = head.pointer;
		temp.pointer = null;
		size--;
	}
	
	// j) replaceAtIndex method
	public void replaceAtIndex(TVShow obj, int i) {
		if (i < 0 || i > (size - 1)) {
			throw new NoSuchElementException("There is no such index"); 		//TODO a reviser
		}
		else if (i == 0){
			ShowNode newNode = new ShowNode(obj, null);
			newNode.pointer = head.pointer;
			head = newNode;							
		}
		else {
			ShowNode newNode = new ShowNode (obj, null);
			ShowNode previous = head;
			int count = 0;
			while (count < (i - 1)) {
				previous = previous.pointer;
				count++;
			}
			ShowNode toBeReplaced = previous.pointer;
			newNode.pointer = toBeReplaced.pointer;
			previous.pointer = newNode;				//TODO should we delete the node that was replaced aka do toBeReplaced = null;
		}
	}
	
	// l) find method
	public ShowNode find(String showIDsearched) {
		numIterations = 0;
		ShowNode found = head;
		while (found.aShow.getShowID() != showIDsearched) {
			numIterations++;									//TODO ADD NULLPOINTEREXCEPTION
			if (found.pointer == null) {
				return null;
			}
			found = found.pointer;
		}
		return found;
	}
	
	// l) contains method
	public boolean contains(String showIDsearched) {
		if (find(showIDsearched) == null) {					//TODO easy simple way check if u like it :)
			return false;
		}
		else 
			return true;
	}
	
	// m) equals method
	public boolean equals(ShowList list) {
		if (this.getSize() != list.getSize()) {
			return false;
		}
		else {
			ShowNode current1 = this.head;
			ShowNode current2 = list.head;
			for (int i = 0 ; i < this.getSize() ; i++) {
				if ((current1.aShow.getShowName() == current2.aShow.getShowName()) && (current1.aShow.getStartTime() == current2.aShow.getStartTime()) && (current1.aShow.getEndTime() == current2.aShow.getEndTime())) {
					current1 = current1.pointer;
					current2 = current2.pointer;
				}
				else {
					return false;
				}	
			}
		}
		return true;
	}
	

	// a) Inner class
	public class ShowNode implements Cloneable {

		// i. private attributes
		private TVShow aShow;
		private ShowNode pointer;		// recheck

		// ii. default constructor
		public ShowNode() {
			this.aShow = null;
			this.pointer = null;
		}

		// iii. parameterized constructor
		public ShowNode(TVShow a, ShowNode b) {
			this.aShow = a;
			this.pointer = b;
		}

		// iv. copy constructor
		public ShowNode(ShowNode obj) {
			this.aShow = obj.aShow;
			this.pointer = obj.pointer;
		}

		//TODO v. clone method


		// vi. accessor and mutator methods
		public TVShow getaShow() {
			return aShow;
		}

		public void setaShow(TVShow aShow) {
			this.aShow = aShow;
		}

		public ShowNode getPointer() {
			return pointer;
		}

		public void setPointer(ShowNode pointer) {
			this.pointer = pointer;
		}

	}





}
