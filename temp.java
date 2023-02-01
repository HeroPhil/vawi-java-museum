public class temp {

    public static void main(String[] args) {
        
        ArrayList<int> array = new ArrayList<int>{1,2,3,4,5,6,7,8,9,10};
        // 

        for (int index = 0; index < array.length; index++) {
            int value = array[index];

            if(index > 0) {
                System.out.println("Ich bin nicht der erste");
            }



            System.out.println(value);
        }


        for (int value : array) {

            System.out.println(value);


        }








        //



    }
    
}
