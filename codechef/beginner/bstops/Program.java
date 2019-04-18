package codechef.beginner.bstops;

import java.io.*;

class Program {
    static class BinarySearchTree {
        BinarySearchTree _left;
        BinarySearchTree _right;
        int _value;
        int _position;

        public BinarySearchTree(int value, int position) {
            this._left = null;
            this._right = null;
            this._value = value;
            this._position = position;
        }

        public int insert(int value) {
            int position;
            if (value > this._value) {
                if (this._right != null) {
                    position = this._right.insert(value);
                } else {
                    position = (2 * this._position) + 1;
                    this._right = new BinarySearchTree(value, position);
                }

            } else if (value < this._value) {
                if (this._left != null) {
                    position = this._left.insert(value);
                } else {
                    position = (2 * this._position);
                    this._left = new BinarySearchTree(value, position);
                }
            } else {
                position = -1;
            }
            return position;
        }

        public void reposition(int current) {
            this._position = current;
            if (this._left != null) {
                this._left.reposition(2 * this._position);
            }
            if (this._right != null) {
                this._right.reposition((2 * this._position) + 1);
            }
        }

        public BinarySearchTree popMin() {
            if (this._left.getLeft() != null) {
                return this._left.popMin();
            } else {
                BinarySearchTree value = this._left;
                this._left = null;
                return value;
            }
        }

        public int erase(int value, BinarySearchTree parent) {
            int position = -1;
            if (value > this._value) {
                if (this._right == null) {
                    return -1;
                } else {
                    return this._right.erase(value, this);
                }
            } else if (value < this._value) {
                if (this._left == null) {
                    return -1;
                } else {
                    return this._left.erase(value, this);
                }
            } else {
                position = this._position;

                BinarySearchTree subLeft = this._left;
                BinarySearchTree subRight = this._right;

                if (subLeft == null && subRight == null) {
                    if (parent == null) {
                        return -1;
                    } else if (parent.getValue() > this._value) {
                        parent.setLeft(null);
                    } else {
                        parent.setRight(null);
                    }
                } else if (subLeft != null && subRight == null) {
                    if (parent == null) {
                        this._value = subLeft.getValue();
                        this._left = subLeft.getLeft();
                        this._right = subLeft.getRight();
                    } else if (parent.getValue() > this._value) {
                        parent.setLeft(subLeft);
                    } else {
                        parent.setRight(subLeft);
                    }
                } else if (subLeft == null && subRight != null) {
                    if (parent == null) {
                        this._value = subRight.getValue();
                        this._left = subRight.getLeft();
                        this._right = subRight.getRight();
                    } else if (parent.getValue() > this._value) {
                        parent.setLeft(subRight);
                    } else {
                        parent.setRight(subRight);
                    }
                } else {
                    int minValue = 0;
                    if (subRight.getLeft() == null) {
                        minValue = subRight.getValue();
                        this._right = subRight.getRight();
                    } else {
                        BinarySearchTree minTree = subRight.popMin();
                        subRight.setLeft(minTree.getRight());
                        minValue = minTree.getValue();
                    }
                    this._value = minValue;
                }
            }
            return position;
        }

        public int getValue() {
            return this._value;
        }

        public void setValue(int value) {
            this._value = value;
        }

        public int getPosition() {
            return this._position;
        }

        public void setPosition(int position) {
            this._position = position;
        }

        public BinarySearchTree getLeft() {
            return this._left;
        }

        public void setLeft(BinarySearchTree left) {
            this._left = left;
        }

        public BinarySearchTree getRight() {
            return this._right;
        }

        public void setRight(BinarySearchTree right) {
            this._right = right;
        }

        @Override
        public String toString() {
            String res = "Node: " + this._value + "\n";
            if (this.getLeft() != null) {
                res += this._value + "Left: \n";
                res += this.getLeft().toString();
            }
            if (this.getRight() != null) {
                res += this._value + "Right: \n";
                res += this.getRight().toString();
            }
            return res;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String lines = br.readLine();
        int n = Integer.parseInt(lines);

        int[] position = new int[n];
        BinarySearchTree root = null;

        for (int i = 0; i < n; i++) {
            String nodeLines = br.readLine();
            String[] nodeValues = nodeLines.trim().split("\\s+");
            int nodeValue = Integer.parseInt(nodeValues[1]);

            if (root == null) {
                root = new BinarySearchTree(nodeValue, 1);
                position[i] = 1;
            } else if (nodeValues[0].equals("i")) {
                position[i] = root.insert(nodeValue);
            } else {
                position[i] = root.erase(nodeValue, null);
                if (position[i] == -1) {
                    root = null;
                    position[i] = 1;
                } else {
                    root.reposition(1);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.println(position[i]);
        }

    }
}