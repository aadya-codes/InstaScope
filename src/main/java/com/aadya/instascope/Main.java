package com.aadya.instascope;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;

import java.io.File;

public class Main extends Application {

    private Stage stage;

    @Override
    public void start(Stage stage) {

        this.stage = stage;

        showUploadScreen();
    }

    private void showUploadScreen() {

        Label title = new Label("InstaScope");

        title.getStyleClass().add("title");

        Label description = new Label("Analyse your Instagram relationships privately.");

        description.getStyleClass().add("subtitle");

        Label instructions = new Label(
                "Step 1\n\n" +
                "Go to Instagram → Settings → Accounts Center\n" +
                "→ Your information and permissions\n" +
                "→ Download your information\n\n" +

                "Select your Instagram account and choose:\n" +
                "• Some of your information\n" +
                "• Followers and following\n" +
                "• JSON format\n\n" +

                "Download the files to your device, then select followers_1.json and following.json below."
        );

        instructions.getStyleClass().add("instruction");

        instructions.setWrapText(true);

        Button followersButton =
        new Button("Choose followers_1.json");

        Button followingButton =
                new Button("Choose following.json");

        followersButton.setPrefWidth(220);
        followingButton.setPrefWidth(220);

        Button analyseButton =
                new Button("Analyse");

        analyseButton.setDisable(true);

        final File[] followersFile = {null};
        final File[] followingFile = {null};

        followersButton.setOnAction(event -> {

            FileChooser fileChooser = new FileChooser();

            fileChooser.setTitle("Select followers_1.json");

            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter(
                            "JSON files",
                            "*.json"
                    )
            );

            File selectedFile =
                    fileChooser.showOpenDialog(stage);

            if (selectedFile != null) {

                followersFile[0] = selectedFile;

                followersButton.setText(
                        "✓ " + selectedFile.getName()
                );

                if (followingFile[0] != null) {
                    analyseButton.setDisable(false);
                }
            }
        });


        followingButton.setOnAction(event -> {

            FileChooser fileChooser = new FileChooser();

            fileChooser.setTitle("Select following.json");

            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter(
                            "JSON files",
                            "*.json"
                    )
            );

            File selectedFile =
                    fileChooser.showOpenDialog(stage);

            if (selectedFile != null) {

                followingFile[0] = selectedFile;

                followingButton.setText(
                        "✓ " + selectedFile.getName()
                );

                if (followersFile[0] != null) {
                    analyseButton.setDisable(false);
                }
            }
        });


        analyseButton.setOnAction(event -> {

            try {

                InstagramParser parser = new InstagramParser();
                AnalysisResult result = parser.parse(
                        followersFile[0],
                        followingFile[0]
                );

                showResultsScreen(result);

            } catch (Exception e) {

                e.printStackTrace();

            }
        });

        VBox layout = new VBox(20);

        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(
                title,
                description,
                instructions,
                followersButton,
                followingButton,
                analyseButton
        );

        Scene scene = new Scene(layout, 600, 500);

        scene.getStylesheets().add(
        getClass().getResource("/style.css").toExternalForm()
        );

        stage.setTitle("InstaScope");
        stage.setScene(scene);
        stage.show();
    }

    private void showResultsScreen(AnalysisResult result) {

        Label title =
                new Label("Your InstaScope Results");

        Label summary =
                new Label(
                        "Here's what we found in your Instagram relationships."
                );

        Label followers =
                new Label("Followers: " + result.getFollowers().size());
        Button showFollowers = new Button("Show usernames");

        Label following =
                new Label("Following: " + result.getFollowing().size());
        Button showFollowing = new Button("Show usernames");

        Label notFollowingBack =
                new Label("Don't follow you back: " + result.getNotFollowingBack().size());
        Button showNotFollowingBack = new Button("Show usernames");

        Label iDontFollowBack =
                new Label("You don't follow back: " + result.getiDontFollowBack().size());
        Button showIDontFollowBack = new Button("Show usernames");

        Label mutuals =
                new Label("Mutuals: " + result.getMutualFollowers().size());
        Button showMutuals = new Button("Show usernames");

        ListView<String> followersList = new ListView<>();
        ListView<String> followingList = new ListView<>();
        ListView<String> notFollowingBackList = new ListView<>();
        ListView<String> iDontFollowBackList = new ListView<>();
        ListView<String> mutualsList = new ListView<>();

        followersList.getStyleClass().add("username-list");
        followingList.getStyleClass().add("username-list");
        notFollowingBackList.getStyleClass().add("username-list");
        iDontFollowBackList.getStyleClass().add("username-list");
        mutualsList.getStyleClass().add("username-list");

        followersList.setPrefHeight(200);
        followingList.setPrefHeight(200);
        notFollowingBackList.setPrefHeight(200);
        iDontFollowBackList.setPrefHeight(200);
        mutualsList.setPrefHeight(200);

        followersList.setVisible(false);
        followingList.setVisible(false);
        notFollowingBackList.setVisible(false);
        iDontFollowBackList.setVisible(false);
        mutualsList.setVisible(false);

        showFollowers.setOnAction(event -> {
            followersList.getItems().setAll(
                    result.getFollowers()
            );
            followersList.setVisible(true);
            followersList.setManaged(true);
        });

        showFollowing.setOnAction(event -> {
            followingList.getItems().setAll(
                    result.getFollowing()
            );
            followingList.setVisible(true);
            followingList.setManaged(true);
        });

        showNotFollowingBack.setOnAction(event -> {
            notFollowingBackList.getItems().setAll(
                    result.getNotFollowingBack()
            );
            notFollowingBackList.setVisible(true);
            notFollowingBackList.setManaged(true);
        });

        showIDontFollowBack.setOnAction(event -> {
            iDontFollowBackList.getItems().setAll(
                    result.getiDontFollowBack()
            );
            iDontFollowBackList.setVisible(true);
            iDontFollowBackList.setManaged(true);
        });

        showMutuals.setOnAction(event -> {
            mutualsList.getItems().setAll(
                    result.getMutualFollowers()
            );
            mutualsList.setVisible(true);
            mutualsList.setManaged(true);
        });

        Button backButton = new Button("Back");

        backButton.setOnAction(event -> {

            showUploadScreen();

        });

        VBox layout = new VBox(20);

        layout.setPadding(new Insets(40));
        layout.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(
                title,
                summary,

                followers,
                showFollowers,
                followersList,

                following,
                showFollowing,
                followingList,

                notFollowingBack,
                showNotFollowingBack,
                notFollowingBackList,

                iDontFollowBack,
                showIDontFollowBack,
                iDontFollowBackList,

                mutuals,
                showMutuals,
                mutualsList,

                backButton
        );

        ScrollPane scrollPane = new ScrollPane(layout);

        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        Scene scene = new Scene(scrollPane, 700, 600);

        scene.getStylesheets().add(
        getClass().getResource("/style.css").toExternalForm()
        );

        stage.setTitle("InstaScope - Results");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch();
    }
}