import java.util.Arrays;

public class Main {
    static final int size = 10000000;
    static final int h = size / 2;

    public static void main(String[] args) {
        variant1(createMassiv());
        variant2(createMassiv());

    }
    private static float[] createMassiv(){
        float[] array = new float[size];
        for (int i=0;i< array.length;i++){
            array[i]=1;
        }
        return array;
    }
    private static void variant1 (float []arr){
        long a = System.currentTimeMillis();
     //   System.out.println(Arrays.toString(arr));
        for (int i=0; i< arr.length;i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
      //  System.out.println(Arrays.toString(arr));
        System.out.println("Время выполнения 1:\n"+(System.currentTimeMillis() - a));

    }

    private static void variant2 (float []arr){
        long a = System.currentTimeMillis();
        //   System.out.println(Arrays.toString(arr));
        float[] a1 = new float[h];
        float[] a2 = new float[h];
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        Thread run1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i< a1.length;i++) {
                    a1[i] = (float) (a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });
        run1.start();

        Thread run2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i< a2.length;i++) {
                    a2[i] = (float) (a2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });
        run2.start();
        try {
            run1.join();
            run2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

      //  System.arraycopy(a1, 0, arr, 0, h);
      //  System.arraycopy(a2, 0, arr, h, h);
      //  System.out.println(Arrays.toString(arr));
        //  System.out.println(Arrays.toString(arr));
        System.out.println("Время выполнения 2:\n"+(System.currentTimeMillis() - a));
    }

}
