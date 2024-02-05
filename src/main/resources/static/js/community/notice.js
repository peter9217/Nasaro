const notice = document.getElementsByClassName('notice-content');
for(let n of notice) {
    /* 공지 게시글 클릭시 */
    n.addEventListener('click', e => {
      const no = e.target.parentElement.getAttribute("value");
      document.location.href="/community/noticeDetail/"+no
    });
  
};