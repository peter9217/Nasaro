const verses = document.getElementsByClassName('verses-content');
for(let v of verses) {
    /* 공지 게시글 클릭시 */
    v.addEventListener('click', e => {
      const noticeNo = e.target.parentElement.getAttribute("value");
      document.location.href="/verses/versesDetail/"+noticeNo
    });
  
};