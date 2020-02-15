@app.route("/post/<int:post_id>")
def post(post_id):
    # Get Location if it exists
    post = Post.query.get_or_404(post_id)
    return jsonify(