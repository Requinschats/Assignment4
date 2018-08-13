/**
 * Names and IDs:
 * 		Myriam Tayah (40074762)
 * 		Olivier Fradette-Roy (40074024)
 * COMP 249
 * Assignment #4
 * Due Date : Saturday August 11, 2018
 */

//--------------------------------------------------------------------
// Assignment 4
// ShowList class and ShowNode inner class
// Written by: Olivier Fradette-Roy 40074024 and Myriam Tayah 40074762
//--------------------------------------------------------------------

import java.util.NoSuchElementException;

/**
 * This class allows the creation of objects of type ShowList.
 * It also has an inner class which creates objects of type ShowNode.
 */
public class ShowList {

	/**
	 * Class attributes.
	 */
	// b)
	private ShowNode head;		
	// c)
	private int size;

	private int numIterations;

	/**
	 * This accessor method allows the retrieval of the number of iterations made in the list.
	 * @return numIterations. An int variable.
	 */
	public int getNumIterations() {
		return numIterations;
	}

	/**
	 * This accessor method allows the retrieval of the size of the list.
	 * @return size. An int attribute.
	 */
	public int getSize() {
		return size;
	}

	// d) default constructor
	/**
	 * This default constructor allows the creation of an empty ShowList object.
	 * The head is assigned null.
	 */
	public ShowList() {
		head = null;
	}

	// e) copy constructor
	/**
	 * This copy constructor allows the creation of a ShowList object through the deep copy of the passed object of the same type.
	 * @param obj. Passed ShowList object to be copied.
	 */
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
	/**
	 * This method allows us to add a node to the beginning of the linkedlist.
	 * The passed TVShow object is used to create a node which is added to the start of the list, as the head. 
	 * @param obj. A TVShow object.
	 */
	public void addToStart(TVShow obj) {
		ShowNode temp = head;
		head = new ShowNode(obj, head);
		head.pointer = temp;
	}

	// g) insertAtIndex method
	/**
	 * This method allows us to add a note at a given index.
	 * @param obj. A TVShow object.
	 * @param i. An int variable representing the index.
	 */
	public void insertAtIndex(TVShow obj, int i) {
		if (i < 0 || i > (size - 1)) {
			throw new NoSuchElementException("There is no such index"); 		
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

	/**
	 * This method allows us to delete a certain node according to its index.
	 * @param i. An int variable representing the the index.
	 */
	// h) deleteFromIndex method
	public void deleteFromIndex(int i) {
		try {
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
		} catch (NullPointerException e) {
			System.out.println("Nothing here.");
		} catch (NoSuchElementException f) {
			System.out.println("No such element.");
		}
	}


	// i) deleteFromStart method
	/**
	 * This method allows us to delete the first node of a linkedlist. 
	 */
	public void deleteFromStart() {		//TODO what are the special cases here????
		ShowNode temp = head;
		try {
			if (head.pointer != null) {
				head = head.pointer;
				temp.pointer = null;
				size--;
			}
			else
				head = null;
		} catch (NullPointerException e) {
			System.out.println("Nothing here.");
		}
	}

	// j) replaceAtIndex method
	/**
	 * This method allows us to replace a node by another at a certain index.
	 * @param obj. A TVShow obj used to create the new node.
	 * @param i. An int variable representing the index of the node to be replaced.
	 */
	public void replaceAtIndex(TVShow obj, int i) {
		if (i < 0 || i > (size - 1)) {
			throw new NoSuchElementException("There is no such index"); 		//TODO a reviser
		}
		else if (i == 0){
			try {
				ShowNode newNode = new ShowNode(obj, null);
				newNode.pointer = head.pointer;
				head = newNode;
			}
			catch (NullPointerException e) {
				System.out.println("Nothing here.");
			}
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
			previous.pointer = newNode;				
		}
	}

	// l) find method
	/**
	 * This method allows us to find a certain node in the ShowList.
	 * @param showIDsearched. ID if the TVShow to be found.
	 * @return found. ShowNode found.
	 */
	public ShowNode find(String showIDsearched) {
		numIterations = 0;
		ShowNode found = head;
		try {
			while (found.aShow.getShowID() != showIDsearched) {
				numIterations++;									
				if (found.pointer == null) {
					return null;
				}
				found = found.pointer;
			}
		} catch (NullPointerException e) {
			System.out.println("Nothing here.");
		} catch (NoSuchElementException f) {
			System.out.println("No such thing.");
		}
		return found;
	}

	// l) contains method
	/**
	 * This method allows us to see if a certain show is to be found within a ShowList.
	 * @param showIDsearched. ID of the TVShow object.
	 * @return True if TVShow is found, false otherwise.
	 */
	public boolean contains(String showIDsearched) {
		if (find(showIDsearched) == null) {					
			return false;
		}
		else 
			return true;
	}

	// m) equals method
	/**
	 * This method compares two ShowList objects, the calling one and the passed one.
	 * @param list. ShowList object, passed.
	 * @return true if objects are equal, false otherwise.
	 */
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
	/**
	 * This inner class allows the creation of ShowNodes objects.
	 * It implements the Cloneable interface.
	 */
	public class ShowNode implements Cloneable {

		// i. private attributes
		/**
		 * ShowNode objects have two attributes, a TVShow object and another ShowNode object.
		 */
		private TVShow aShow;
		private ShowNode pointer;		

		// ii. default constructor
		/**
		 * This default constructor allows the creation of an empty node.
		 */
		public ShowNode() {
			this.aShow = null;
			this.pointer = null;
		}

		// iii. parameterized constructor
		/**
		 * This parameterized constructor allows the creation of a ShowNode object.
		 * The ShowNode object can be null if there are no following nodes.
		 * @param a. A TVShow object.
		 * @param b. A ShowNode object.
		 */
		public ShowNode(TVShow a, ShowNode b) {
			this.aShow = a;
			this.pointer = b;
		}

		// iv. copy constructor
		/**
		 * This copy constructor allows the creation of a ShowNode object by deep copy of the passed one.
		 * @param obj. A ShowNode object.
		 */
		public ShowNode(ShowNode obj) {
			this.aShow = obj.aShow;
			this.pointer = obj.pointer;
		}

		// v. clone method
		/**
		 * This clone method allows the cloning of a calling ShowNode using a copy constructor.
		 * @return It returns the new ShowNode object.
		 */
		public ShowNode clone() {
			ShowNode clone = new ShowNode(this.aShow, this.pointer);
			return clone;
		}

		// vi. accessor and mutator methods
		/**
		 * This accessor method allows the retrieval of the TVShow obejct parameter of the ShowNode.
		 * @return aShow.
		 */
		public TVShow getaShow() {
			return aShow;
		}

		/**
		 * This mutator method allows the assignation of a TVShow object for the aShow attribute.
		 * @param aShow. A TVShow object.
		 */
		public void setaShow(TVShow aShow) {
			this.aShow = aShow;
		}

		/**
		 * This accessor method allows the retrieval of the pointer of a ShowNode.
		 * @return pointer. A ShowNode object.
		 */
		public ShowNode getPointer() {
			return pointer;
		}

		/**
		 * This mutator method allows the assignation of a ShowNode object as the pointer.
		 * @param pointer. A ShowNode object.
		 */
		public void setPointer(ShowNode pointer) {
			this.pointer = pointer;
		}

	}





}
