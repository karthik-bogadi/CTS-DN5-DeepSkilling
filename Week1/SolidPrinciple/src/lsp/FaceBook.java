package lsp;

public class FaceBook implements SocialMedia,SocialPostAndMediaManager,VideoCallManager{
    @Override
    public void chatWithFriend() {
        System.out.println("Chat in FaceBook");
    }

    @Override
    public void sendPhotosAndVideos() {
        System.out.println("send photos in facebook");
    }

    @Override
    public void publishPost(Object post) {
        System.out.println("posts on facebook");
    }

    @Override
    public void groupVideoCall(String... users) {
        System.out.println("Video call on facebook");
    }
}
