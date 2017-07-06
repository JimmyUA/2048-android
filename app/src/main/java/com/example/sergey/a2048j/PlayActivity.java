package com.example.sergey.a2048j;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;

import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static com.example.sergey.a2048j.PlayField.LOG_TAG;



public class PlayActivity extends AppCompatActivity {
    private User2048 user;
    private int size = 4;
    private Cell[][] buttons;
    private int buttonSize;
    private TextView scoreValue;
    private TextView recordValue;
    private ImageView syncButton;
    private LinearLayout main;

    // Colors
    private final static int C2 = Color.rgb(31,31,31);
    private final static int C4 = Color.rgb(47, 79, 79);
    private final static int C8 = Color.rgb(46, 139, 87);
    private final static int C16 = Color.rgb(0, 100, 0);
    private final static int C32 = Color.rgb(85, 107, 47);
    private final static int C64 = Color.rgb(139, 0, 0);
    private final static int C132 = Color.rgb(220, 20, 60);
    private final static int C256 = Color.rgb(199, 21, 133);
    private final static int C512 = Color.rgb(255, 105, 180);
    private final static int C1024 = Color.rgb(255, 99, 71);
    private final static int C2048 = Color.rgb(255, 215, 0);
    private final static int C4096 = Color.rgb(0, 255, 2);
    private final static int CDEF = Color.rgb(169, 169, 169);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        Log.d(LOG_TAG, "getParcelableExtra");

        // Getting user object from LOG_TAG

        user = (User2048)getIntent().
                getParcelableExtra(User2048.class.getCanonicalName());
        Log.d(LOG_TAG, "user: " + user.name + ", "
                + user.login + ", " + user.password + ", " + user.email);


        scoreValue = (TextView)findViewById(R.id.scoreShowTextView);
        recordValue = (TextView)findViewById(R.id.recordShowTextView);
        syncButton = (ImageView)findViewById(R.id.syncButton);

        //Getting field Size from intent
        size =Integer.parseInt(getIntent().getStringExtra("size"));
        //scoreValue.setText("" + size);

        setGameParams();
        recordValue.setText("" + user.getRecord().getRecord());
        buttons = new Cell[size][size];
        choseButtonsSize();

        //Find buttons layout
        LinearLayout buttonsLayout = (LinearLayout) findViewById(R.id.buttonsLayout);
        buttonsLayout.setOrientation(LinearLayout.VERTICAL);

        main = (LinearLayout) findViewById(R.id.main);

        initButtons(buttonsLayout);
        //set4FieldFromDB();
        syncButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sync();
            }
        });
        setOnSwipeListener(buttonsLayout);




    }

    private int pxToDp(int px)
    {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    // Chose buttons size
    private void choseButtonsSize(){
        switch (size){
            case 4:
                buttonSize = 235;
                break;
            case 5:
                buttonSize = 195;
                break;
            case 6:
                buttonSize = 155;
                break;
            case 8:
                buttonSize = 100;
                break;
        }
    }

    // Create buttons to store them in array
    private void initButtons(LinearLayout buttonsLayout){
        //int padding = buttonSize/2;
        for (int i = 0; i < size; i++){
            LinearLayout row = new LinearLayout(this);
            row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            row.setGravity(Gravity.CENTER);
            for (int j = 0; j < size; j ++){
                buttons[i][j] = new Cell(this);
                buttons[i][j].setLayoutParams(new LayoutParams(buttonSize, buttonSize));
                buttons[i][j].setText(" ");
                updateButtonState(i, j);
                choseColor(i, j);
                TextView border = new TextView(this);
                border.setWidth(10);
                border.setHeight(buttons[i][j].getHeight());
                border.setBackgroundColor(Color.BLACK);
                row.addView(buttons[i][j]);
                row.addView(border);

            }

            TextView border = new TextView(this);
            border.setWidth(buttonSize * size);
            border.setHeight(10);
            border.setBackgroundColor(Color.WHITE);
            buttonsLayout.addView(row);
            buttonsLayout.addView(border);
        }
    }

    // choses color depending on tile value
   private void choseColor(int i, int j){
        Tile[][] tiles = user.getField().getTilesArray();
        if(!user.getField().isNull(tiles[i][j])){
        switch (tiles[i][j].getValue()) {
            case 2:
                buttons[i][j].setBackgroundColor(C2);
                break;
            case 4:
                buttons[i][j].setBackgroundColor(C4);
                break;
            case 8:
                buttons[i][j].setBackgroundColor(C8);
                break;
            case 16:
                buttons[i][j].setBackgroundColor(C16);
                break;
            case 32:
                buttons[i][j].setBackgroundColor(C32);
                break;
            case 64:
                buttons[i][j].setBackgroundColor(C64);
                break;
            case 128:
                buttons[i][j].setBackgroundColor(C132);
                break;
            case 256:
                buttons[i][j].setBackgroundColor(C256);
                break;
            case 512:
                buttons[i][j].setBackgroundColor(C512);
                break;
            case 1024:
                buttons[i][j].setBackgroundColor(C1024);
                break;
            case 2048:
                buttons[i][j].setBackgroundColor(C2048);
                break;
            case 4096:
                buttons[i][j].setBackgroundColor(C4096);
                break;
            default:
                break;
        }

        } else {
            buttons[i][j].setBackgroundColor(CDEF);
        }
    }

    // create a play field and set it to user
    public void setGameParams(){
        PlayField field = new PlayField(size);
        user.setField(field);
        user.setScore(new Score());

        user.setRecord(new Record());
        setRecordFromDB();
        if (!setFieldFromDB()) {
            field.setTilesArray();
        }
        int scoreToSet = user.getScore().getScore();
        scoreValue.setText("" + scoreToSet);
    }

    // Updates text and color according to tiles values
    private void updateButtonState(int i, int j){
        PlayField field = user.getField();
        Tile[][] tiles = field.getTilesArray();

        if (!field.isNull(tiles[i][j])){
            buttons[i][j].setText(""+ tiles[i][j].getValue());
            choseColor(i, j);
        }
        else{
            buttons[i][j].setText("");
            choseColor(i, j);
        }
    }

    private void updateButtonSState(){
        for (int i = 0; i < buttons.length; i++){
            for (int j = 0; j < buttons.length; j++){
                updateButtonState(i, j);
            }
        }
    }

    private void setOnSwipeListener(LinearLayout layout){
        layout.setOnTouchListener(new OnSwipeTouchListener(PlayActivity.this){
            public void onSwipeTop() {
                moveTop();
            }
            public void onSwipeRight() {
                moveRight();
            }
            public void onSwipeLeft() {
                moveLeft();
            }
            public void onSwipeBottom() {
               moveDown();
            }
        });
    }

    //updates field and score
    private void sync(){

                user.getField().setTilesArray();
                user.getScore().setScore(0);
                updateButtonSState();
    }

    private void moveDown() {
        PlayField field = user.getField();
        if (field.moveToTheBottom()) {
            addTile();
        }
        updateButtonSState();
        if (isGameOver()) {
            gameOver();
            return;
        }
        updateScore();
    }


    private void moveLeft() {
        PlayField field = user.getField();
        if (field.moveToTheLeft()) {
            addTile();
        }
        updateButtonSState();
        if (isGameOver()) {
            gameOver();
            return;
        }
        updateScore();
    }

    private void moveRight() {
        PlayField field = user.getField();
        if (field.moveToTheRight()) {
            addTile();
        }
        updateButtonSState();
        if (isGameOver()) {
            gameOver();
            return;
        }
        updateScore();
    }

    // making step up
    private void moveTop() {
        PlayField field = user.getField();
        if (field.moveUp()) {
            addTile();
        }
        updateButtonSState();
        if (isGameOver()) {
            gameOver();
            return;
        }
        updateScore();
    }

    // Updates score on a screen
    private void updateScore(){
        int score = user.getScore().getScore();
//        if (score > user.getRecord().getRecord()){
//            recordValue.setText("" + score);
//        }
        scoreValue.setText("" + score);
    }

    // Adds one tile on the PlayField
    private void addTile(){
        PlayField field = user.getField();
        field.addTile(new Tile((int) (Math.random() * (field.getSize() - 1)), (int) (Math.random() * field.getSize())));
    }
    // checks if game is over (need to be checked after each move)
    private boolean isGameOver(){
        if (user.getField().isFull() && !user.getField().isStepsAvailable()){
            return true;
        } else {
            return false;
        }
    }
    // make nessasary actions after game is over
    private void gameOver() {
        saveNewRecord();
        user.getField().setTilesArrayToNulls();
        user.getScore().setScore(0);
        showGameOverLayout();
    }

    private void showGameOverLayout() {
        // creating game over layout
        LinearLayout gameOverLayout = new LinearLayout(this);
        gameOverLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        gameOverLayout.setGravity(Gravity.CENTER);
        gameOverLayout.setBackgroundColor(Color.BLACK);

        // adding Text View
        TextView gameOverText = new TextView(this);
        gameOverText.setText("Game over!");
        gameOverText.setTextColor(Color.argb(255, 230, 230, 230));
        gameOverText.setTextSize(28);



        main.removeAllViews();
        main.addView(gameOverLayout);
        gameOverLayout.addView(gameOverText);

    }


    ////////////////////////////////////////////////////////////////////////////////////
    //                     Super overrided methods                                    //
    ///////////////////////////////////////////////////////////////////////////////////

    // Saves parameters when exiting layout
    @Override
    protected void onDestroy(){
        super.onDestroy();
        //saveNewRecord();
        saveFieldState();
    }

    // Saves parameters when exiting game
    @Override
    protected void onPause(){
        super.onPause();
        //saveNewRecord();
        saveFieldState();
    }



    ////////////////////////////////////////////////////////////////////////////////////
    //                     Save data in DB methods                                    //
    ///////////////////////////////////////////////////////////////////////////////////

    // Saves new record value into the DB
    private void saveNewRecord(){
        if (user.getField().getScore().getScore() >= user.getRecord().getRecord()) {
            DBRecordWorker worker = new DBRecordWorker(this);
            SQLiteDatabase dbw = worker.getWritableDatabase();
            //ContentValues cv = new ContentValues();
            //cv.put("record", user.getField().getScore().getScore());
            //dbw.insert(worker.TABLE_NAME, null, cv);
            dbw.execSQL("UPDATE " + worker.TABLE_NAME + " SET record = "
                    + user.getField().getScore().getScore() + " WHERE id = 1");
            dbw.close();
        }
    }

    // Saves field state
    private void saveFieldState(){
        switch (size){
            case 4:
                save4Field();
                break;
            case 5:
                save5Field();
                break;
            case 6:
                save6Field();
                break;
            case 8:
                save8Field();
                break;
        }
    }

    // Saves 4x4 field in DB
    private void save4Field() {
        saveAllFields("saved4");

    }

    // Saves 5x5 field in DB
    private void save5Field() {
        saveAllFields("saved5");

    }

    // Saves 6x6 field in DB
    private void save6Field() {
        saveAllFields("saved6");

    }

    // Saves 8x8 field in DB
    private void save8Field() {
        saveAllFields("saved8");

    }

    private void saveAllFields(String tableName){
        DBSaveFieldWorker worker = new DBSaveFieldWorker(this);
        SQLiteDatabase dbw = worker.getWritableDatabase();
        dbw.execSQL("UPDATE " + tableName + " SET field = '"
                + user.getField().getPlayFieldString() + "', score = '" +
                        user.getScore().getScore() + "' WHERE id = 1");
        dbw.close();
    }

    ////////////////////////////////////////////////////////////////////////////////////
    //                     Set data from DB methods                                  //
    ////////////////////////////////////////////////////////////////////////////////////

    // Sets record for user from DB
    private void setRecordFromDB(){
        DBRecordWorker worker = new DBRecordWorker(this);
        SQLiteDatabase dbr = worker.getReadableDatabase();
        String selectQuery = "SELECT record FROM " + worker.TABLE_NAME + " WHERE id = 1";
        Cursor cursor = dbr.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            int record = Integer.parseInt(cursor.getString(0));
            user.getRecord().setRecord(record);
        }
    }

    // Saves field state
    private boolean setFieldFromDB(){
        switch (size){
            case 4:
                return set4FieldFromDB();
            case 5:
                return set5FieldFromDB();
            case 6:
                return set6FieldFromDB();
            case 8:
                return set8FieldFromDB();
        }
        return false;
    }

    private boolean set8FieldFromDB() {
        return setAllFieldFromDB("saved8");
    }

    private boolean set6FieldFromDB() {
        return setAllFieldFromDB("saved6");
    }

    private boolean set5FieldFromDB() {
        return setAllFieldFromDB("saved5");
    }

    private boolean set4FieldFromDB() {
        return setAllFieldFromDB("saved4");
    }

    private boolean setAllFieldFromDB(String tableName) {
        DBSaveFieldWorker worker = new DBSaveFieldWorker(this);
        SQLiteDatabase dbr = worker.getReadableDatabase();
        String selectQueryScore = "SELECT score FROM " + tableName + " WHERE id = 1";
        Cursor cursorScore = dbr.rawQuery(selectQueryScore, null);
        if (cursorScore.moveToFirst()) {
            user.getScore().setScore(cursorScore.getInt(0));
        }
        String selectQuery = "SELECT field FROM " + tableName + " WHERE id = 1";
        Cursor cursor = dbr.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            String fieldStateString = cursor.getString(0);
            if (fieldStateString.length() > 2){
                String[] fieldStateStringArray = fieldStateString.split(", ");
                int count = 0;
                int [][] fieldStateIntArray = new int[size][size];
                for (int i = 0; i < size; i++){
                    for (int j = 0; j < size; j++){
                        fieldStateIntArray[i][j] = Integer.parseInt(fieldStateStringArray[count]);
                        count++;
                    }
                }
                user.getField().setSavedTilesArray(fieldStateIntArray);
                return true;

            }
        }
        return false;
    }
}



