import { useEffect, useRef, useState } from 'react'
import DropDown from './DropDown'
import classes from './Modal.module.css'
import Cookies from 'js-cookie';
import { languages, scores } from '../../util/ScoreNLanaguage';

export default function Modal({year, semester, selectedCourseListHandler, infoHandler})
{
    const modalLanguages = languages.map(element => element.name);
    const modalScores = scores.map(element => element.name);
    const [language, setLanguage] = useState("한국어")
    const [score, setScore] = useState("성적을 선택하세요")
    const [courseList, setCourseList] = useState([])
    const [selectedCourse, setSelectedCourse] = useState(null)
    async function searchCourse(event)
    {
        let name = event.target.value
        if(name.trim() != "")
        {
            try 
            {
                const response = await fetch(`http://localhost:8080/courses/search?year=${year}&semester=${semester}&name=${name}`, {
                    method: 'GET',
                    headers: {
                    'Content-Type': 'application/json'
                    },
                    credentials: 'include',
                });
                if (!response.ok) 
                {
                    if(response.status == 404)
                    {
                        setCourseList(null);
                    }
                    console.log(response);
                }
                else
                {
                    const data = await response.json();
                    console.log(data)
                    setCourseList(data)
                }
            } catch (error) {
                console.error('에러:', error);
            }
        }
        else
        {
            setCourseList([])
        }
    } 
    function select(value)
    {
        setCourseList([])
        setSelectedCourse(value)
    }
    async function addScore()
    {
        console.log("fsdf")
        let languageIndex = modalLanguages.findIndex(element => element === language)
        let scoreIndex = modalScores.findIndex(element => element === score)
        const data = {
            "name" : selectedCourse.name,
            "credit" : selectedCourse.credit,
            "language" : languages[languageIndex].value,
            "score" : scores[scoreIndex].value,
            "stAcademicTerm" : {
                "academicYear" : year,
                "semester" : semester,
            }
        }
        const csrf = Cookies.get("XSRF-TOKEN");    
        try {
            const response = await fetch('http://localhost:8080/user/enrollments', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'X-XSRF-TOKEN' : csrf,
                },
                body : JSON.stringify(data),
                credentials: 'include',
            });
            if (!response.ok) {
                console.log(response);
                throw new Error('실패');
            }
            else
            {
                let responseData = await response.json();
                console.log(responseData)
                selectedCourseListHandler(prevList => {
                    let array = [...prevList, responseData]
                    infoHandler(array)
                    return array;
                });
                var myModalEl = document.getElementById('exampleModal');
                var myModal = bootstrap.Modal.getInstance(myModalEl);
                myModal.hide();      
            }
        } catch (error) {
        console.error('에러:', error);
        }
    }
    return(
            <div className={`modal ${classes.wrapper}`} id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div className="modal-dialog modal-dialog-centered">
                    <div className="modal-content">
                        <div className="modal-header">
                            <div>
                                <h2 className="modal-title fs-5 fw-bold" id="exampleModalLabel">{year}년 {semester} 과목 추가</h2>
                                <small className='text-secondary'>수강한 과목 정보를 입력해주세요.</small>
                            </div>
                            <button style={{height:"0", width:"0"}} type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div className="modal-body">
                            <div className='d-grid gap-4'>
                                <div className='row'>
                                    <div className='col-3 d-flex align-items-center'>
                                        <label for="exampleFormControlInput1" className="form-label fw-bold mb-0">과목 검색</label>
                                    </div>
                                    <div className='col-9'>
                                        <div className='position-relative'>
                                            <input onChange={(event)=>{searchCourse(event)}} type="email" className="form-control" id="exampleFormControlInput1" placeholder="과목명을 입력하세요"></input>
                                            <div style={{maxHeight:"20em", overflowY:"auto"}} className='position-absolute top-100 start-0 bg-white rounded-3 shadow-sm z-3 w-100'>
                                            {
                                                courseList === null ? 
                                                <div className={`p-3 border-bottom border-light-subtle`}>
                                                    <div style={{fontSize:"0.9em"}} className='text-black text-opacity-50'>검색 결과가 없습니다.</div>
                                                </div>
                                                :
                                                courseList.map((value, index)=>{
                                                    return(
                                                        <div onClick={()=>select(value)} className={`${classes.courseList} p-3 border-bottom border-light-subtle`}>
                                                            <div className='fw-bold'>{value.name}</div>
                                                            <div style={{fontSize:"0.9em"}} className='text-black text-opacity-50'>{value.credit}학점</div>
                                                        </div>
                                                    )
                                                })
                                            }
                                            </div>
                                        </div>

                                    </div>
                                </div>
                                {
                                    selectedCourse &&
                                    <div className='row'>
                                        <div className='col-3 d-flex align-items-center'>
                                            <label for="exampleFormControlInput1" className="form-label fw-bold mb-0">선택된 과목</label>
                                        </div>
                                        <div className='col-9'>
                                            <div className='bg-primary p-2 rounded bg-opacity-10'>
                                                <div className='fw-bold'>{selectedCourse.name}</div>
                                                <div style={{fontSize:"0.9em"}} className='text-black text-opacity-50'>{selectedCourse.credit}학점</div>
                                            </div>
                                        </div>
                                    </div>
                                }
                                <div className='row'>
                                    <div className='col-3 d-flex align-items-center'>
                                        <label className="form-label fw-bold mb-0">강의 언어</label>
                                    </div>
                                    <div className='col-9'>
                                        <DropDown defaultSelect={language} list={modalLanguages} width={"6.5rem"} handleSelect={setLanguage} ></DropDown>
                                    </div>
                                </div>
                                <div className='row'>
                                    <div className='col-3 d-flex align-items-center'>
                                        <label className="form-label fw-bold mb-0">성적</label>
                                    </div>
                                    <div className='col-9'>
                                        <DropDown defaultSelect={score} list={modalScores} handleSelect={setScore} width={score === "성적을 선택하세요" ? "11rem" : "5rem"} ></DropDown>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="modal-footer">
                            <button onClick={(event) => addScore(event)} type="button" className="btn btn-secondary fw-bolder">추가하기</button>
                        </div>
                    </div>
                </div>
            </div>
    )
}