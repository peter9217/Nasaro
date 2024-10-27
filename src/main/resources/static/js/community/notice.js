const notice = document.getElementsByClassName('notice-content');
for(let n of notice) {
    /* 공지 게시글 클릭시 */
    n.addEventListener('click', e => {
      const no = e.target.parentElement.getAttribute("value");
      if (no!=null) {
        document.location.href="/community/noticeDetail/"+no
        
      }
    });
  
};

//  공지 수정하기 버튼
if(document.getElementById("noticeModify") != null) {
  document.getElementById("noticeModify").addEventListener("click", e => {
    e.preventDefault(); // 기존의 클릭 동작을 취소함

    const token = document.getElementById("csrf").value
    const name = document.getElementById("csrfName").value
    const noticeNo = document.getElementById("noticeModify").getAttribute("value");

    // 새로운 폼 엘리먼트 생성
    const form = document.createElement('form');
    form.setAttribute('method', 'post');
    form.setAttribute('action', '/notice/modify/' + noticeNo);

    // 토큰을 담을 hidden input 생성
    const tokenInput = document.createElement('input');
    tokenInput.setAttribute('type', 'hidden');
    tokenInput.setAttribute('name', name);
    tokenInput.setAttribute('value', token);

    // 폼에 hidden input 추가
    form.appendChild(tokenInput);

    // 폼을 body에 추가하고 submit
    document.body.appendChild(form);
    form.submit();
  });
}





/* 공지사항 삭제 버튼 */
if (document.getElementById("noticeDelete") != null) {
  document.getElementById("noticeDelete").addEventListener("click", e=>{
    const noticeNo = e.target.value;
    
    if (confirm("삭제하시겠습니까?")) {
        fetch("/notice/delete", {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
              [csrfHeader]: csrfToken
            },
            body: JSON.stringify(noticeNo),
            })
        
        .then(response => response.text())
        .then(() => {
            setTimeout(function() {
                document.location.href = "/community/notice";
            }, 500);
        })
        .catch(e => {
            console.log(e);
        });
    }
  })
}