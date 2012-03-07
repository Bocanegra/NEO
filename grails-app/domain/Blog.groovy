class Blog extends Content {

  static hasMany = [posts: Post]

  static mapping = {
    table 'Blogs'
  }

  static constraints = {
  }

  String toString() {
    "Blog with ${posts?.size()} posts"
  }

  //A�ade un post en el blog de la empresa
  def addPost(String sender, String title, String text){
    this.addToPosts(new Post(author:sender, title:title, text:text))
    log.info "New post added."
  }

  //Modifica un post del blog de la empresa
  def modifyPost(String idPost, String user, String title, String text){
    //Solo el propietario del post o el PU de la empresa lo podr�n modificar
    def result=true
    def post=Post.get(idPost)
    if(!post){
      log.info "Post not found."
      result=false
    }else{
      //Se comprueba si el usuario es PU de la empresa a la que pertenece el blog
      def esPU=post.blog.sme.primaryUser.equals(user)
      if(esPU || post.author.equals(user)){
        post.modify(title, text)
        log.info "Post modified."
      }else{
        //No es el autor del post ni el PU de la empresa.
        log.info "Post not found."
        result=false
      }
    }
    result
  }

 //Elimina un post del blog de la empresa.
  boolean deletePost(String idPost, String user){
    def result=true
    def post=Post.get(idPost)
    if(!post){
      log.info "Post not found."
      result=false
    }else{
      //Se comprueba si el usuario es PU de la empresa a la que pertenece el blog
      def esPU=post.blog.sme.primaryUser.equals(user)
      if(esPU || post.author.equals(user)){
        this.removeFromPosts(post)
        post.delete()
        log.info "Post deleted."
      }else{
        //No es el autor del post ni el PU de la empresa.
        log.info "Post not found."
        result=false
      }
    }
    result
  }

  def returnLastPosts(String nmax, String noffset) {
    Post.findAllByBlog(this, [max:Integer.parseInt(nmax), offset:Integer.parseInt(noffset), sort:'dateCreated', order:'desc'])
  }

  def returnPostsFromLastLogout(Date logout) {
    Post.findAllByBlogAndDateCreatedGreaterThan(this, logout)
  }

}
