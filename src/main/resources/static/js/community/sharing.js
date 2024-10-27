const sharing = document.getElementsByClassName('sharing-content');
for(let s of sharing) {
    /* 나눔 게시글 클릭시 */
    s.addEventListener('click', e => {
      const no = e.target.parentElement.getAttribute("value");
      if (no!=null) {
        document.location.href="/community/sharingDetail/"+no
      }
    });
  
};

//  나눔 수정하기 버튼
if(document.getElementById("sharingModify")!=null){
  document.getElementById("sharingModify").addEventListener("click", e=>{
    e.preventDefault(); // 기존의 클릭 동작을 취소함

    const token = document.getElementById("csrf").value
    const name = document.getElementById("csrfName").value
    const sharingNo = document.getElementById("sharingModify").getAttribute("value");

    // 새로운 폼 엘리먼트 생성
    const form = document.createElement('form');
    form.setAttribute('method', 'post');
    form.setAttribute('action', '/sharing/modify/' + sharingNo);

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
  })
}





/* 나눔 삭제 버튼 */
if (document.getElementById("sharingDelete") != null) {
  document.getElementById("sharingDelete").addEventListener("click", e=>{
    const sharingNo = e.target.value;
    const token = document.getElementById("csrf").value
    const name = document.getElementById("csrfName").value
    if (confirm("삭제하시겠습니까?")) {
      fetch("/sharing/delete", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
            [csrfHeader]: csrfToken
          },
          body: JSON.stringify(sharingNo),
        })
      
      .then(response => response.text())
      .then(() => {
          setTimeout(function() {
              document.location.href = "/community/sharing";
          }, 500);
      })
      .catch(e => {
          console.log(e);
      });
    }
  })
}