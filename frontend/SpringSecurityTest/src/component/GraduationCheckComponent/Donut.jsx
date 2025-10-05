export default function Donut({ value = 81, color = 'var(--bs-danger)', size = 96, backgroundColor, })
{
  const ring = {
    width: size, height: size, borderRadius: '50%',
    background: `conic-gradient(${color} ${value}%, ${backgroundColor} 0)`
  };
  const hole = {
    width: size*0.8, height: size*0.8
  };
  return (
    <div className="position-relative d-inline-grid" style={{placeItems:'center', ...ring}}>
      <div className="bg-white rounded-circle" style={hole}></div>
      <span className="position-absolute fw-bold" style={{color, fontSize:"1.1em"}}>{value}%</span>
    </div>
  );
}