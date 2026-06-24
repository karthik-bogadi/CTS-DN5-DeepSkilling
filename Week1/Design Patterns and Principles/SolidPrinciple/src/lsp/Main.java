package lsp;

public class Main {
    public static void main(String[] args){
        SocialMedia obj=new FaceBook();
        SocialPostAndMediaManager obj2=new FaceBook();
        VideoCallManager obj3=new Whatsap();
        obj.chatWithFriend();
        obj.sendPhotosAndVideos();
        obj2.publishPost(new FaceBook());


        obj3.groupVideoCall("karthik","Dilip","Chaitanya");
    }
}
