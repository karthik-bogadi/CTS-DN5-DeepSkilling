package lsp;

import java.util.Arrays;

public class Whatsap implements VideoCallManager,SocialMedia{
    @Override
    public void chatWithFriend() {

    }

    @Override
    public void sendPhotosAndVideos() {

    }

    @Override
    public void groupVideoCall(String... users) {
        System.out.println(Arrays.toString(users));
    }
}
