public class BowlingGame {

    public int getBowlingScore(String bowlingCode) {
        int sum = 0;
        int[] score = new int[10];
        int[][] record = new int[11][2];
        String[] arr = bowlingCode.split("\\|\\|");
        getRecord(arr[0], record);

        if (arr.length > 1) {
            parse(arr[1], record[10]);
        }

        compute(record, score);
        for (int i = 0; i < 10; i++) {
            sum += score[i];
        }
        return sum;
    }
    
     private void getRecord(String s, int[][] record) {
        String[] arr = s.split("\\|");
        for (int i = 0; i < 10; i++) {
            parse(arr[i], record[i]);
        }
    }

    private void parse(String score, int[] num) {
        char[] arr = score.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 'X') {
                num[i] = 10;
            }
            if (arr[i] == '-') {
                num[i] = 0;
            }
            if ('0' <= arr[i] && arr[i] <= '9') {
                num[i] = arr[i] - 48;
            }
            if (arr[i] == '/') {
                num[i] = 10 - num[0];
            }
        }
    }

    private void compute(int[][] record, int[] score) {
        for (int i = 0; i < 9; i++) {
            if (record[i][0] == 10) {
                if (record[i + 1][0] == 10)
                    score[i] = 10 + record[i + 1][0] + record[i + 2][0];
                else
                    score[i] = 10 + record[i + 1][0] + record[i + 1][1];
            } else {
                if (record[i][0] + record[i][1] == 10) {
                    score[i] = 10 + record[i + 1][0];
                } else {
                    score[i] = record[i][0] + record[i][1];
                }
            }
        }
        score[9] = record[9][0] + record[9][1] + record[10][0] + record[10][1];
    }
}
