import classes from './CardBox.module.css'


export default function CardBox({svg, tilte, content, index})
{
    return(
        <div className="col">
            <div className={`d-flex flex-column gap-0 row-gap-4 border border-secondary-subtle bg-white rounded-4 shadow-sm py-4 h-100 ${classes.box}`}>
                <center>
                    <div style={{height:"3.0em", width:"3.0em"}} className={`d-flex justify-content-center align-items-center rounded-3 ${classes[`iconBox${index}`]} mb-2`}>
                        {svg}
                    </div>
                    <p className="fw-bold fs-5 m-0">{tilte}</p>
                </center>
                <center>
                    <p className="m-0 text-secondary px-3 fs-6">{content}</p>
                </center>
            </div>
        </div>
    )
}