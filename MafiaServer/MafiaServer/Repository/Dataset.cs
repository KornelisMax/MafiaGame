using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace MafiaServer.Repository
{
    public static class Dataset
    {
        public static void AddSamplePlayersToDb(MafiaContext _context)
        {
            _context.Players.Add(new Models.Player { ID = new Guid("49a20b2e-f469-4614-1649-08d7f90d4954"), IsAlive = true, Name = "Tomas", Role = "Mafia"}); 
            _context.Players.Add(new Models.Player { ID = new Guid("49a20b2e-f469-4614-1649-08d7f90d4955"), IsAlive = true, Name = "Romas", Role = "Mafia" }); 
            _context.Players.Add(new Models.Player { ID = new Guid("49a20b2e-f469-4614-1649-08d7f90d4956"), IsAlive = true, Name = "Kapitomas", Role = "Mafia" }); 
            _context.Players.Add(new Models.Player { ID = new Guid("49a20b2e-f469-4614-1649-08d7f90d4957"), IsAlive = true, Name = "Adomas", Role = "Civil" }); 
            _context.Players.Add(new Models.Player { ID = new Guid("49a20b2e-f469-4614-1649-08d7f90d4958"), IsAlive = true, Name = "Omas", Role = "Civil" }); 
            _context.Players.Add(new Models.Player { ID = new Guid("49a20b2e-f469-4614-1649-08d7f90d4959"), IsAlive = true, Name = "Rudas", Role = "Civil" }); 
            _context.Players.Add(new Models.Player { ID = new Guid("49a20b2e-f469-4614-1649-08d7f90d4950"), IsAlive = true, Name = "szudas", Role = "Civil" }); 
            _context.Players.Add(new Models.Player { ID = new Guid("49a20b2e-f469-4614-1649-08d7f90d4953"), IsAlive = true, Name = "yolo", Role = "Civil" }); 
            _context.Players.Add(new Models.Player { ID = new Guid("49a20b2e-f469-4614-1649-08d7f90d4952"), IsAlive = true, Name = "trysdu", Role = "Civil" }); 
            _context.Players.Add(new Models.Player { ID = new Guid("49a20b2e-f469-4614-1649-08d7f90d4951"), IsAlive = true, Name = "keturi", Role = "Civil" });
            _context.SaveChanges();
        }
    }
}
