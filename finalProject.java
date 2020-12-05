import java.util.*;
public class finalProject {
    public static void main(String[] args){
        int[] arr = {2, -8, 3, -2, 4, -10};
        System.out.print("Given the array: ");
        printArray(arr);
        System.out.println();
        System.out.print("The max subarray is: ");
        System.out.println("\nThe maximum continuous sum is "+maxSubArray(arr));


        binTree tree = new binTree();
        tree.root1 = new node(42);
        tree.root1.rightChild = new node(31);
        tree.root1.rightChild.rightChild = new node(9);
        tree.root1.leftChild = new node(5);
        tree.root1.leftChild.leftChild = new node(2);
        tree.root1.leftChild.leftChild.rightChild = new node(23);
        tree.root1.leftChild.rightChild = new node(1);


        tree.root2 = new node(5);
        tree.root2.rightChild = new node(1);
        tree.root2.leftChild = new node(2);
        tree.root2.leftChild.rightChild = new node(23);

        if (tree.isSubtree(tree.root1, tree.root2))
            System.out.println("Tree 2 is a subtree of Tree 1 ");
        else
            System.out.println("Tree 2 is not a subtree of Tree 1");

        int arr1[][] = new int[][] {{1, 2, -1, -4, -20},
                                    {-8, -3, 4, 2, 1},
                                    {3, 8, 10, 1, 3},
                                    {-4, -1, 1, 7, -6}};
        System.out.println("The maximum sum of the max submatrix is "+maxSubMatrix(arr1));
    }



    //question 2
    public static int maxSubArray(int arr[]){
        int max = Integer.MIN_VALUE;
        int temp = 0;
        int tempIndex = 0;
        int startIndex = 0;
        int endIndex = 0;

        for (int i = 0; i < arr.length; i++){
            temp = temp + arr[i];
            if (max < temp) {
                max = temp;
                startIndex = tempIndex;
                endIndex = i;
            }
            if (temp < 0) {
                temp = 0;
                tempIndex = i+1;
            }
        }

        for(int i=startIndex; i<=endIndex; i++){
            System.out.print(arr[i]+" ");
        }
        return max;
    }

    //question 3
    static class node{
        int val;
        node leftChild, rightChild, rightRChild;

        node(int item){
            val = item;
            leftChild = null;
            rightChild = null;
            rightRChild = null;
        }
    }
    static class binTree {
        node root1;
        node root2;

        boolean check(node root1, node root2) {
            if (root1 == null && root2 == null)
                return true;
            if (root1 == null || root2 == null)
                return false;
            return (root1.val == root2.val && check(root1.leftChild, root2.leftChild)
                    && check(root1.rightChild, root2.rightChild));
        }

        boolean isSubtree(node tree1, node tree2) {
            if (tree2 == null)
                return true;
            if (tree1 == null)
                return false;
            if (check(tree1, tree2))
                return true;

            return isSubtree(tree1.leftChild, tree2) || isSubtree(tree1.rightChild, tree2);
        }
    }

    //question 4
    private static int maxSubMatrix(int[][] arr) {
        int tempSum[][] = new int[arr.length + 1][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                tempSum[i + 1][j] = tempSum[i][j] + arr[i][j];
            }
        }

        int max = -1;
        int min = Integer.MIN_VALUE;
        int rowIndex = 0;
        int colIndex = 0;
        int rowIndexS = 0;
        int rowIndexE = 0;
        int colIndexS = 0;
        int colIndexE = 0;
        for(int i = 0; i < arr.length; i++){
            for(int j = i; j < arr.length; j++){
                int sum = 0;
                int curColStart = 0;
                for(int k = 0; k < arr[0].length; k++){
                    sum += tempSum[j + 1][k] - tempSum[i][k];
                    if(sum < 0){
                        if (min < sum){
                            min = sum;
                            rowIndex = j;
                            colIndex = k;
                        }
                        sum = 0;
                        curColStart = k + 1;
                    }
                    else if (max < sum){
                        max = sum;
                        rowIndexS = i;
                        rowIndexE = j;
                        colIndexS = curColStart;
                        colIndexE = k;
                    }
                }
            }
        }

        if (max == -1){
            for(int i=rowIndex; i<rowIndex; i++){
                for(int j=colIndex; j<colIndex; j++){
                    if(j==colIndex)
                        System.out.print("|"+arr[i][j]+" ");
                    else if(j==colIndex)
                        System.out.print(arr[i][j]+"|");
                    else
                        System.out.print(arr[i][j]+" ");
                }
                System.out.println();
            }
        }
        else{
            for(int i=rowIndexS; i<=rowIndexE; i++){
                for(int j=colIndexS; j<=colIndexE; j++){
                    if(j==colIndexS)
                        System.out.print("|"+arr[i][j]+" ");
                    else if(j==colIndexE)
                        System.out.print(arr[i][j]+"|");
                    else
                        System.out.print(arr[i][j]+" ");
                }
                System.out.println();
            }
        }

        if(max==-1)
            max = min;
        return max;
    }

    public static void printArray(int[] arr){
        for(int i=0; i<arr.length; i++){
            if(i==0){
                System.out.print("["+arr[i]);
                continue;
            }
            if(i==arr.length-1){
                System.out.print(", "+arr[i]+"]");
                break;
            }
            System.out.print(", "+arr[i]);
        }
    }
}


