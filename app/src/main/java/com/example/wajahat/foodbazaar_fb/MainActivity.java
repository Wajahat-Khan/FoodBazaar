package com.example.wajahat.foodbazaar_fb;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BlurMaskFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.wajahat.foodbazaar_fb.Adapters.StartCategoriesAdapter;
import com.example.wajahat.foodbazaar_fb.Data.Categories;
import com.example.wajahat.foodbazaar_fb.Data.Items;
import com.example.wajahat.foodbazaar_fb.Data.Order;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private List<Categories> allCategories = new ArrayList<>();
    private List<Items> order_list = new ArrayList<>();
    private Order order_object = new Order();

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private DatabaseReference itemReference;
    private DatabaseReference inventoryReference;

    private ChildEventListener childEventListener;


    private StartCategoriesAdapter startCategoriesAdapter;
    private RecyclerView categoriesrecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }

        // Initialize Firebase components

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Categories");
        databaseReference.keepSynced(true);
        itemReference = firebaseDatabase.getReference().child("Items");
        itemReference.keepSynced(true);
        inventoryReference=firebaseDatabase.getReference().child("Inventory");
/*
        inventoryReference.child("Cajun").setValue("50");
        inventoryReference.child("Cajun").setValue("50");
        inventoryReference.child("Potato").setValue("500");
        inventoryReference.child("Garlic mayo").setValue("50");
        inventoryReference.child("Calamari").setValue("50");
        inventoryReference.child("Flour").setValue("50");
        inventoryReference.child("Shrimps").setValue("500");
        inventoryReference.child("Buffalo Sauce").setValue("50");
        inventoryReference.child("Ranch sauce").setValue("50");
        inventoryReference.child("Pepper sauce").setValue("50");
        inventoryReference.child("Chicken").setValue("100");
        inventoryReference.child("Tomato").setValue("200");
        inventoryReference.child("Capsicum").setValue("200");
        inventoryReference.child("Cheese").setValue("200");
        inventoryReference.child("herbs").setValue("100");
        inventoryReference.child("Cream cheese").setValue("50");
        inventoryReference.child("Honey mustard").setValue("50");
        inventoryReference.child("Parmesan").setValue("50");
        inventoryReference.child("Peri Peri").setValue("50");
        inventoryReference.child("Flour tortilla").setValue("50");
        inventoryReference.child("Salsa").setValue("50");
        inventoryReference.child("Sour Cream").setValue("50");
        inventoryReference.child("Bread").setValue("200");
        inventoryReference.child("Garlic herb").setValue("50");
        inventoryReference.child("Olive Oil").setValue("50");
        inventoryReference.child("Mozeralla cheese").setValue("50");
        inventoryReference.child("Marinara sauce").setValue("50");
        inventoryReference.child("Macaroni").setValue("50");
        inventoryReference.child("Cheddar Cheese").setValue("200");
        inventoryReference.child("mayo").setValue("50");
        inventoryReference.child("Onion").setValue("100");
        inventoryReference.child("Beef Chilli").setValue("50");
        inventoryReference.child("Bacon").setValue("50");
        inventoryReference.child("Jalapeno").setValue("500");
        inventoryReference.child("Onion").setValue("100");
        inventoryReference.child("Mushrooms").setValue("100");
        inventoryReference.child("Meatballs").setValue("100");
        inventoryReference.child("Tomato sauce").setValue("50");
        inventoryReference.child("Slider buns").setValue("100");
        inventoryReference.child("Bun").setValue("200");
        inventoryReference.child("Beef").setValue("300");
        inventoryReference.child("Chilli").setValue("50");
        inventoryReference.child("Fish").setValue("100");
        inventoryReference.child("Coke").setValue("500");
        inventoryReference.child("7up").setValue("500");
        inventoryReference.child("Fanta").setValue("500");
        inventoryReference.child("Fresh Lime 7up").setValue("500");
        inventoryReference.child("Red Bull").setValue("500");
        inventoryReference.child("Sting").setValue("500");
        inventoryReference.child("Perrier Water").setValue("500");
        inventoryReference.child("Mineral Water").setValue("500");
*/
/*

        Categories categories=new Categories(1,"Starters","Starters,Fish Starters,Chicken Starters,Cheese Starters,Veggie Starters,Others","https://firebasestorage.googleapis.com/v0/b/foodbazaar-fb.appspot.com/o/Categories%2Fstarters.png?alt=media&token=118899f5-5002-4bc7-9a55-5a2ffe4019eb");
        databaseReference.child("1").setValue(categories);
        categories=new Categories(2,"Burgers","Burgers,Chicken,Beef,Fish","https://firebasestorage.googleapis.com/v0/b/foodbazaar-fb.appspot.com/o/Categories%2Fburgers.png?alt=media&token=fcbf8b53-825c-48e5-b9d1-e7219da8013f");
        databaseReference.child("2").setValue(categories);
        categories=new Categories(3,"Beverages","Beverages,Soft Drinks,Energy Drinks,Water","https://firebasestorage.googleapis.com/v0/b/foodbazaar-fb.appspot.com/o/Categories%2Fbeverages.png?alt=media&token=590ac531-37c0-4975-8290-b9b08e7c73c0");
        databaseReference.child("3").setValue(categories);

         categories=new Categories(4,"Sandwiches","Beverages,Soft Drinks,Energy Drinks,Water","https://firebasestorage.googleapis.com/v0/b/foodbazaar-fb.appspot.com/o/Categories%2Fsandwiches.png?alt=media&token=423959c6-6f5b-4bf7-b4b0-960f6697c582");
        databaseReference.child("4").setValue(categories);
        categories=new Categories(5,"Soups","Beverages,Soft Drinks,Energy Drinks,Water","https://firebasestorage.googleapis.com/v0/b/foodbazaar-fb.appspot.com/o/Categories%2Fsoup.png?alt=media&token=16d78352-a8b6-43de-b9b3-6c46f6c333c9");
        databaseReference.child("5").setValue(categories);

*/
/*
        Items item = new Items(1,"Cajun Fried Calamari","Calamari rings dipped in cajun batter, deep fried to golden brown.","Calamari rings dipped in cajun batter, deep fried to golden brown. Served with french fries & garlic-mayo dipping sauce",495,true,"https://firebasestorage.googleapis.com/v0/b/foodbazaar-fb.appspot.com/o/Starters%2FCajun%20Fried%20Calamari.jpg?alt=media&token=38fb12bd-d900-4aaa-a9df-a67890273642"
                ,null,"Fish Starters","Starters",4,"Cajun-0.5,Potato-1,Garlic mayo-0.25,Calamari-2,Flour-0.5",0);
        itemReference.child("1").setValue(item);

         item = new Items(2,"Buffalo Shrimps","Battred shrimps tossed in our spicy buffalo sauce.","Battred shrimps tossed in our spicy buffalo sauce. Served with french fries and ranch dipping sauce.",645,true,"https://firebasestorage.googleapis.com/v0/b/foodbazaar-fb.appspot.com/o/Starters%2FBuffalo%20Shrimps.jpg?alt=media&token=bf5c648d-d0d0-43ad-ac2c-7a1ffb53a2b7"
                ,null,"Fish Starters","Starters",5,"Shrimps-8,Buffalo Sauce-0.25,Ranch sauce-0.25",0);
        itemReference.child("2").setValue(item);

        item = new Items(3,"Dynamite Shrimps","Crispy battered fried shrimps tossed in our explosive spicy pepper sauce.","Crispy battered fried shrimps tossed in our explosive spicy pepper sauce.",645,true,"https://firebasestorage.googleapis.com/v0/b/foodbazaar-fb.appspot.com/o/Starters%2FDynamite%20Shrimps.jpg?alt=media&token=c80efc41-6a23-4b32-ae5d-55083c8b9306"
                ,null,"Fish Starters","Starters",4,"Shrimps-8,Pepper sauce-0.25",0);
        itemReference.child("3").setValue(item);

        item = new Items(4,"Loaded Potato Skins","Baked potato skins filled with jalapeno peppers","Baked potato skins filled with jalapeno peppers, grilled chicken, tomato, capsicum & cheese. Served with salsa & sour cream.",425,true,"https://firebasestorage.googleapis.com/v0/b/foodbazaar-fb.appspot.com/o/Starters%2FLoaded%20Potato%20Skins.jpg?alt=media&token=5f41442c-97fe-4447-abbc-a7f99a824639"
                ,null,"Chicken Starters","Starters",4,"Potato-2,Jalapeno-3,Chicken-0.5,Tomato-1,Capsicum-0.5,Cheese-2",0);
        itemReference.child("4").setValue(item);

        item = new Items(5,"Stuffed Chicken Strips","Chicken tenderloins stuffed with cream cheese & herbs.","Chicken tenderloins stuffed with cream cheese & herbs. Served with honey mustard dipping sauce.",495,true,"https://firebasestorage.googleapis.com/v0/b/foodbazaar-fb.appspot.com/o/Starters%2FStuffed%20Chicken%20Strips.jpg?alt=media&token=2417367c-3e26-4908-9a19-9d4fc243e2a7"
                ,null,"Chicken Starters","Starters",4,"Chicken-0.5,Garlic herb-0.25,Cream cheese-0.5,Honey mustard-0.25",0);
        itemReference.child("5").setValue(item);

        item = new Items(6,"Buffalo Combo","Combination of buffalo chicken bites, peri-peri chicken bites, parmesan chicken bites and french fries.","Combination of buffalo chicken bites, peri-peri chicken bites, parmesan chicken bites and french fries. Served with ranch dipping sauce.",695,true,"https://firebasestorage.googleapis.com/v0/b/foodbazaar-fb.appspot.com/o/Starters%2FBuffalo%20Combo.jpg?alt=media&token=32224a31-0de6-4b66-8211-8e76f6b13e03"
                ,null,"Chicken Starters","Starters",5,"Chicken-0.75,Parmesan-0.25,Peri Peri-0.25,Buffalo Sauce-0.25,Ranch sauce-0.25,Potato-1",0);
        itemReference.child("6").setValue(item);

        item = new Items(7,"Chicken Quesadillas","Folded in a perfectly tossed flour tortilla and served with salsa and sour cream.","Folded in a perfectly tossed flour tortilla and served with salsa and sour cream.",495,true,"https://firebasestorage.googleapis.com/v0/b/foodbazaar-fb.appspot.com/o/Starters%2FChicken%20Quesadillas.jpeg?alt=media&token=101d7df8-cc0b-4edb-aa99-32616c2a1d61"
                ,null,"Chicken Starters","Starters",5,"Chicken-0.25,Flour tortilla-2,Salsa-0.25,Sour Cream-0.25",0);
        itemReference.child("7").setValue(item);

        item = new Items(8,"Parmesan Bread Sticks","Four Pieces of our freshly baked bread sticks,topped with parmesan.","Four Pieces of our freshly baked bread sticks,topped with parmesan, garlic herb & olive oil.",295,true,"https://firebasestorage.googleapis.com/v0/b/foodbazaar-fb.appspot.com/o/Starters%2FParmesan%20Bread%20Sticks.jpg?alt=media&token=66d8e07e-40bd-4384-ac50-27841eef0999"
                ,null,"Cheese Starters","Starters",5,"Bread-4,Parmesan-1,Garlic herb-0.25,Olive Oil-0.25",0);
        itemReference.child("8").setValue(item);


        item = new Items(9,"Mozzarella Parmigiana","Golden fried sliced mozzarella cheese, topped with spicy marinara sauce","Golden fried sliced mozzarella cheese, topped with spicy marinara sauce and parmesan cheese. Drizzled with basil oil.",445,true,"https://firebasestorage.googleapis.com/v0/b/foodbazaar-fb.appspot.com/o/Starters%2FMozzarella%20Parmigiana.jpg?alt=media&token=06e34a3c-6e44-41b0-8934-b495b2b5cfc5"
                ,null,"Cheese Starters","Starters",3,"Mozeralla cheese-2,Parmesan-1,Marinara sauce-0.25",0);
        itemReference.child("9").setValue(item);

        item = new Items(10,"Mac & Cheese","Macaroni pasta tossed with white cheddar and parmesan cheese sauce.","Macaroni pasta tossed with white cheddar and parmesan cheese sauce. Topped with bread crumbs and baked.",445,true,"https://firebasestorage.googleapis.com/v0/b/foodbazaar-fb.appspot.com/o/Starters%2FMac%20%26%20Cheese.jpg?alt=media&token=2b07cd21-015d-410a-a3c1-cca6f45c3c9a"
                ,null,"Cheese Starters","Starters",3,"Macaroni-0.5,Parmesan-0.5,Cheddar Cheese-0.5",0);
        itemReference.child("10").setValue(item);

        item = new Items(11,"Texas Red Chilli","Made from scratch, topped with cheddar cheese, diced onions and sour cream.","Made from scratch, topped with cheddar cheese, diced onions and sour cream.",495,true,"https://firebasestorage.googleapis.com/v0/b/foodbazaar-fb.appspot.com/o/Starters%2FTexas%20Red%20Chilli.jpg?alt=media&token=0226bd19-a478-46a1-b2a7-5ebb9ca6e814"
                ,null,"Cheese Starters","Starters",3,"Cheddar Cheese-0.5,Sour Cream-0.25,Onion-0.5",0);
        itemReference.child("11").setValue(item);


        item = new Items(12,"Buffalo Chicken Bites","Boneless chicken wings tossed with spicy buffalo wing sauce.","Boneless chicken wings tossed with spicy buffalo wing sauce. Served with french fries and ranch dipping sauce.",495,true,"https://firebasestorage.googleapis.com/v0/b/foodbazaar-fb.appspot.com/o/Starters%2FBuffalo%20Chicken%20Bites.jpg?alt=media&token=36e4c29d-41bf-43bb-9535-f852f18c347e"
                ,null,"Cheese Starters","Starters",3,"Chicken-0.25,Buffalo Sauce-0.25,Ranch sauce-0.25,Potato-1",0);
        itemReference.child("12").setValue(item);

        item = new Items(13,"Peri-Peri Chicken Bites","Boneless chicken wings tossed with spicy peri-peri sauce.","Boneless chicken wings tossed with spicy peri-peri sauce. Served with french fries and ranch dipping sauce.",495,true,"https://firebasestorage.googleapis.com/v0/b/foodbazaar-fb.appspot.com/o/Starters%2FPeri-Peri%20Chicken%20Bites.jpg?alt=media&token=7b5f5c27-8c92-4b77-ae26-5cba1b751070"
                ,null,"Cheese Starters","Starters",5,"Chicken-0.5,Peri Peri Sauce-0.25,Ranch sauce-0.25,Potato-0.25",0);
        itemReference.child("13").setValue(item);

        item = new Items(14,"Fry Basket","Golden fried onion rings & french fries.","Golden fried onion rings & french fries. Served with paprika-mayo & bbq dipping sauce.",495,true,"https://firebasestorage.googleapis.com/v0/b/foodbazaar-fb.appspot.com/o/Starters%2FFry%20Basket.jpg?alt=media&token=9ba6164a-4545-4a4d-92bb-9a1215b73222"
                ,null,"Veggie Starters","Starters",4,"Onion-2,mayo-0.25,Potato-2",0);
        itemReference.child("14").setValue(item);

        item = new Items(15,"Roasters Chilli Fries","Our famous cheese fries dusted with cajun spices, topped with homemade beef chilli, sliced jalapenos.","Our famous cheese fries dusted with cajun spices, topped with homemade beef chilli, sliced jalapenos, spicy garlic mayo sauce and scallions.",495,true,"https://firebasestorage.googleapis.com/v0/b/foodbazaar-fb.appspot.com/o/Starters%2FRoasters%20Chilli%20Frie.jpg?alt=media&token=0a00f368-805c-4d34-8dc7-6aed0ef3215a"
                ,null,"Veggie Starters","Starters",5,"Cheddar Cheese-1,Cajun-0.5,Beef Chilli-0.25,Jalapeno-2",0);
        itemReference.child("15").setValue(item);


        item = new Items(16,"Peri-Peri Bacon & Mashroom Fries","French fries with veal bacon, sauteed cheddar cheese mushrooms, and sliced jalapenos.","French fries with veal bacon, sauteed cheddar cheese mushrooms, and sliced jalapenos dusted with peri-peri spice, topped with chipotle ranch sauce.",495,true,"https://firebasestorage.googleapis.com/v0/b/foodbazaar-fb.appspot.com/o/Starters%2FPeri-Peri%20Bacon%20%26%20Mashroom%20Fries%2C.jpg?alt=media&token=af1ff21d-2de1-4ef9-89b6-62d3ea0ca961"
                ,null,"Veggie Starters","Starters",4,"Cheddar Cheese-0.5,Bacon-1,Mushrooms-3,Jalapeno-2",0);
        itemReference.child("16").setValue(item);

        item = new Items(17,"Meatball Sliders","Three mini meatball burgers with mozzarella cheese.","Three mini meatball burgers with mozzarella cheese. Topped with our homemade tomato sauce.",595,true,"https://firebasestorage.googleapis.com/v0/b/foodbazaar-fb.appspot.com/o/Starters%2FMeatball%20Sliders.jpg?alt=media&token=d00828d5-a82b-452e-ade3-5ce75780b9f8"
                ,null,"Others","Starters",4,"Mozzarella Cheese-0.5,Meatballs-3,Tomato sauce-0.25,Slider buns-3",0);
        itemReference.child("17").setValue(item);


        item = new Items(18,"Roasters Famous Trio","Combination of our top sellers, stuff chicken strips, buffalo bites.","Combination of our top sellers, stuff chicken strips, buffalo bites, cajun fried calamari and french fries. Served with dipping sauce.",695,true,"https://firebasestorage.googleapis.com/v0/b/foodbazaar-fb.appspot.com/o/Starters%2FRoasters%20Famous%20Trio.jpg?alt=media&token=dfc265f3-8095-492a-b1c2-55f7af93478a"
                ,null,"Others","Starters",4,"Cajun-0.5,Garlic mayo-0.25,Calamari-2,Flour-0.5,Chicken-0.5,Buffalo Sauce-0.25,Ranch sauce-0.25,herbs-0.25,Cream cheese-0.5,Honey mustard-0.25",0);
        itemReference.child("18").setValue(item);


        // burgers


        item = new Items(19,"Explosion Chicken Burger","Our Big Mouth crispy chicken burger, topped with cheddar cheese","Our Big Mouth crispy chicken burger, topped with cheddar cheese and our explosion spicy ranch dressing.",595,true,"https://firebasestorage.googleapis.com/v0/b/foodbazaar-fb.appspot.com/o/Chicken%2FExplosion%20Chicken%20Burger.jpg?alt=media&token=255ccaea-c58f-4be7-9218-fbe923a771df"
                ,null,"Chicken","Burgers",4,"Chicken-0.5,Cheddar cheese-0.25,Ranch sauce-0.25,Bun-1",0);
        itemReference.child("19").setValue(item);

        item = new Items(20,"Western Bbq Chicken Burger","Our Big Mouth crispy chicken burger with cheddar cheese, crispy onions.","Our Big Mouth crispy chicken burger with cheddar cheese, crispy onions and smoked bbq sauce in a sesame seed bun.",595,true,"https://firebasestorage.googleapis.com/v0/b/foodbazaar-fb.appspot.com/o/Chicken%2FWestern%20Bbq%20Chicken%20Burger.jpg?alt=media&token=6f3f25be-d3e8-4468-8dc4-2e903f231886"
                ,null,"Chicken","Burgers",5,"Chicken-0.25,Cheddar Cheese-0.25,Bun-1,Onion-1",0);
        itemReference.child("20").setValue(item);

        item = new Items(21,"Chicken Royal Burger","Our Big Mouth crispy chicken burger topped with paprika-mayo sauteed onions, mushrooms.","Our Big Mouth crispy chicken burger topped with paprika-mayo sauteed onions, mushrooms, beef bacon & cheddar cheese in a sesame seed bun.",595,true,"https://firebasestorage.googleapis.com/v0/b/foodbazaar-fb.appspot.com/o/Chicken%2FChicken%20Royal%20Burger.png?alt=media&token=8818dad2-0290-48a5-a5db-81744cd1b954"
                ,null,"Chicken","Burgers",4,"Chicken-0.25,Cheddar cheese-0.25,Bun-1,Onion-1",0);
        itemReference.child("21").setValue(item);

        item = new Items(22,"Jalapeno Bacon Chicken Burger","Our Big Mouth crispy chicken burger, topped with sauteed mushrooms.","Our Big Mouth crispy chicken burger, topped with sauteed mushrooms, onions, jalapeno peppers, veal bacon & cheddar cheese in a sesame seed bun.",610,true,"https://firebasestorage.googleapis.com/v0/b/foodbazaar-fb.appspot.com/o/Chicken%2FJalapeno%20Bacon%20Chicken%20Burger.jpg?alt=media&token=7c88359d-0454-4cd9-b3f0-d13b4e535954"
                ,null,"Chicken","Burgers",5,"Mozzarella Cheese-0.5,Meatballs-3,Tomato sauce-0.25,Slider buns-3",0);
        itemReference.child("22").setValue(item);

        item = new Items(23,"Chicken Parmesan Burger","Our Big Mouth crispy chicken burger, topped with crispy fried mozzarella cheese","Our Big Mouth crispy chicken burger, topped with crispy fried mozzarella cheese, marinara sauce and parmesan cheese in a seame seed bun.",610,true,"https://firebasestorage.googleapis.com/v0/b/foodbazaar-fb.appspot.com/o/Chicken%2FChicken%20Parmesan%20Burger.jpg?alt=media&token=886c4f29-9724-4f5f-8d34-021ca8c33444"
                ,null,"Chicken","Burgers",4,"Cheddar Cheese-1,Cajun-0.5,Beef Chilli-0.25,Jalapeno-2",0);
        itemReference.child("23").setValue(item);


        item = new Items(24,"Classic Beef & Cheese","Char-grilled beef burger with cheddar cheese in a sesame seed bun.","Char-grilled beef burger with cheddar cheese in a sesame seed bun.",545,true,"https://firebasestorage.googleapis.com/v0/b/foodbazaar-fb.appspot.com/o/Chicken%2FClassic%20Beef%20%26%20Cheese2.jpg?alt=media&token=3553f3d1-6406-42aa-b48f-ad3757f4d1fc"
                ,null,"Beef","Burgers",5,"Beef-0.25,Cheddar Cheese-0.25,Bun-1,Onion-1",0);
        itemReference.child("24").setValue(item);

        item = new Items(25,"Western Bbq Burger","Char-grilled beef burger with cheddar cheese, beef bacon, crispy straw onions,","Char-grilled beef burger with cheddar cheese, beef bacon, crispy straw onions, smoked bbq sauce in a sesame seed bun.",575,true,"https://firebasestorage.googleapis.com/v0/b/foodbazaar-fb.appspot.com/o/Chicken%2Fwestern%20bbq%20burger.jpg?alt=media&token=dc33cc5c-db7b-477e-a1b5-4022141e108f"
                ,null,"Beef","Burgers",3,"Beef-0.25,Cheddar Cheese-0.25,Bun-1,Bacon-0.5,Onion-1",0);
        itemReference.child("25").setValue(item);

        item = new Items(26,"Jumbo Chilli Cheese Burger","Char-grilled beef burger with cheddar cheese & homemade.","Char-grilled beef burger with cheddar cheese & homemade Texas Beef Chilli in a sesame seed bun.",595,true,"https://firebasestorage.googleapis.com/v0/b/foodbazaar-fb.appspot.com/o/Chicken%2FJumbo%20Chilli%20Cheese%20Burger.jpg?alt=media&token=a2d0e876-be3b-473f-bd2a-e3b8c0974ed0"
                ,null,"Beef","Burgers",5,"Beef-0.25,Cheddar Cheese-0.25,Bun-1,Bacon-0.5,Onion-1",0);
        itemReference.child("26").setValue(item);

        item = new Items(27,"Philly Cheese Burger","Char-grilled beef burger with cheddar cheese & Philly Steak Mix in a sesame seed bun.","Char-grilled beef burger with cheddar cheese & Philly Steak Mix in a sesame seed bun.",595,true,"https://firebasestorage.googleapis.com/v0/b/foodbazaar-fb.appspot.com/o/Chicken%2FPhilly%20Cheese%20Burge.jpg?alt=media&token=b078f94f-01b7-481a-a3f6-133ffea9023e"
                ,null,"Beef","Burgers",5,"Beef-0.25,Cheddar Cheese-0.25,Bun-1",0);
        itemReference.child("27").setValue(item);

        item = new Items(28,"Mushroom Beef Burger","Char-grilled beef burger with cheddar cheese & sauteed mushrooms in a sesame seed bun.","Char-grilled beef burger with cheddar cheese & sauteed mushrooms in a sesame seed bun.",595,true,"https://firebasestorage.googleapis.com/v0/b/foodbazaar-fb.appspot.com/o/Chicken%2Fmushroom%20beef%20burger%20.jpg?alt=media&token=6dbcbad7-4ac5-41e4-816f-94cbf1083559"
                ,null,"Beef","Burgers",4,"Beef-0.25,Cheddar Cheese-0.25,Bun-1,Mushroom-1",0);
        itemReference.child("28").setValue(item);

        item = new Items(29,"Gourmet Fish Burger","Crispy fried fish fillet, topped with cheddar, spicy cocktail sauce.","Crispy fried fish fillet, topped with cheddar, spicy cocktail sauce, lettuce, tomato, onions & mayo.",645,true,"https://firebasestorage.googleapis.com/v0/b/foodbazaar-fb.appspot.com/o/Chicken%2Fgourmet%20fish%20burger2.jpg?alt=media&token=6a196fb9-be88-4288-b031-7cd339da832f"
                ,null,"Fish","Burgers",4,"Fish-0.5,Cheddar Cheese-0.25,Bun-1",0);
        itemReference.child("29").setValue(item);

        // Beverages

        item = new Items(30,"Coke","Single Servings","Single Servings",95,true,"https://firebasestorage.googleapis.com/v0/b/foodbazaar-fb.appspot.com/o/cold%20beverages%2Fcoke.jpg?alt=media&token=fd6ae3b5-95b6-425f-af83-9238600da30d"
                ,null,"Soft Drinks","Beverages",4,"Coke-1",0);
        itemReference.child("30").setValue(item);

        item = new Items(31,"7up","Single Servings","Single Servings",95,true,"https://firebasestorage.googleapis.com/v0/b/foodbazaar-fb.appspot.com/o/cold%20beverages%2F7up2.jpg?alt=media&token=559b2aef-f685-46c8-8aca-3f7fb49ec63b"
                ,null,"Soft Drinks","Beverages",4,"7up-1",0);
        itemReference.child("31").setValue(item);

        item = new Items(32,"Fanta","Single Servings","Single Servings",95,true,"https://firebasestorage.googleapis.com/v0/b/foodbazaar-fb.appspot.com/o/cold%20beverages%2Ffanta.jpg?alt=media&token=4ca1f134-a228-4d69-8080-f4d6f34233f9"
                ,null,"Soft Drinks","Beverages",4,"Fanta-1",0);
        itemReference.child("32").setValue(item);

        item = new Items(33,"Fresh Lime 7up","Single Servings","Single Servings",95,true,"https://firebasestorage.googleapis.com/v0/b/foodbazaar-fb.appspot.com/o/cold%20beverages%2Ffresh%20lime%207up2.png?alt=media&token=5a32980f-b855-49bf-9201-0fabea6d507c"
                ,null,"Soft Drinks","Beverages",4,"Fresh Lime 7up-1",0);
        itemReference.child("33").setValue(item);

        item = new Items(34,"Red Bull","Single Servings","Single Servings",225,true,"https://firebasestorage.googleapis.com/v0/b/foodbazaar-fb.appspot.com/o/cold%20beverages%2Fredbull.jpg?alt=media&token=d8cfd459-17e0-4a37-bee2-58a770408039"
                ,null,"Energy Drinks","Beverages",4,"Red Bull-1",0);
        itemReference.child("34").setValue(item);

        item = new Items(35,"Sting","Single Servings","Single Servings",110,true,"https://firebasestorage.googleapis.com/v0/b/foodbazaar-fb.appspot.com/o/cold%20beverages%2Fsting.png?alt=media&token=b6af737e-a3c2-4058-a136-d68e397aec0d"
                ,null,"Energy Drinks","Beverages",4,"Sting-1",0);
        itemReference.child("35").setValue(item);

        item = new Items(36,"Perrier Water","Single Servings","Single Servings",225,true,"https://firebasestorage.googleapis.com/v0/b/foodbazaar-fb.appspot.com/o/cold%20beverages%2Fperrier%20water.png?alt=media&token=5b976222-f9c5-4cb1-8b78-805b2f7c6157"
                ,null,"Water","Beverages",4,"Perrier Water",0);
        itemReference.child("36").setValue(item);

        item = new Items(37,"Mineral Water","Single Servings","Single Servings",105,true,"https://firebasestorage.googleapis.com/v0/b/foodbazaar-fb.appspot.com/o/cold%20beverages%2Fmineral%20water.png?alt=media&token=8aea7b68-951f-4acb-901c-e143ae27eee0"
                ,null,"Water","Beverages",4,"Mineral Water",0);
        itemReference.child("37").setValue(item);
*/
        ImageView logo=findViewById(R.id.logo);
       // Glide.with(getApplicationContext()).load(R.drawable.logo).into(logo);

        GlideApp.with(getApplicationContext())
                .load(R.drawable.logo).into(logo);
        categoriesrecyclerView = findViewById(R.id.all_categories);
        startCategoriesAdapter = new StartCategoriesAdapter(this, new StartCategoriesAdapter.AdapterListener() {
            @Override
            public void onClick(View view, int position, List<Categories> cat) {
                allCategories = cat;
                Categories temp = allCategories.get(position);
                String subCats = temp.getSub_categories();
                String name = temp.getName();
                String subCategories[] = subCats.split(",");
                Intent intent = new Intent(getBaseContext(), com.example.wajahat.foodbazaar_fb.SecondActivity.class);
                order_object.setOrder_list(order_list);

                intent.putExtra("order_list", order_object);
                intent.putExtra("subCategories", subCategories);
                intent.putExtra("category", name);
                startActivityForResult(intent, 1);
            }

            @Override
            public void onClick(View catView, int adapterPosition) {

            }
        });
        categoriesrecyclerView.setAdapter(startCategoriesAdapter);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
        ZoomLayoutManager zoomLayoutManager = new ZoomLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        categoriesrecyclerView.setLayoutManager(zoomLayoutManager);



        childEventListener = new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Categories category = dataSnapshot.getValue(Categories.class);
                allCategories.add(category);
                startCategoriesAdapter.setCategories(allCategories);


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        databaseReference.addChildEventListener(childEventListener);

        //startCategoriesAdapter.setCategories(allCategories);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    order_object = (Order) bundle.getSerializable("order_list");
                    order_list = order_object.getOrder_list();
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
    }
}
