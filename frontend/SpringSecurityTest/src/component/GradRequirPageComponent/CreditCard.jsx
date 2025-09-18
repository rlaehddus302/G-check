
export default function Card({fontColor, credit, content, ...Prop})
{
    return(
        <div className="col">
            <div {...Prop}>
                <div style={{color:fontColor}} className="fs-4 fw-bold">{credit}</div>
                <div className="fs-6">{content}</div>
            </div>
        </div>
    )
}