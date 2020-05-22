using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace MafiaServer.Repository
{
    public static class Dataset
    {
        public static void AddGameSessionToDb(MafiaContext _context)
        {
            _context.GameSessions.Add(new Models.GameSession()
            {
                GameSessionId = new Guid("49a20b2e - f469 - 4614 - 1649 - 08d7f90d4954"),
                RoomId = new Guid("49a20b2e - f469 - 4614 - 1649 - 08d7f90d4960"),
                GameTime = TimeSpan.FromMinutes(1),
                VoteTime = TimeSpan.FromMinutes(1)
            });
            _context.SaveChanges();
        }
        public static void AddSamplePlayersToDb(MafiaContext _context)
        {
            _context.Rooms.Add(new Models.Room { RoomId = new Guid("49a20b2e-f469-4614-1649-08d7f90d4960") });
            _context.Players.Add(new Models.Player { PlayerId = new Guid("49a20b2e-f469-4614-1649-08d7f90d4955"), RoomId = new Guid("49a20b2e-f469-4614-1649-08d7f90d4960") ,IsAlive = true, Name = "Romas", Role = "Mafia" }); 
            _context.Players.Add(new Models.Player { PlayerId = new Guid("49a20b2e-f469-4614-1649-08d7f90d4956"), RoomId = new Guid("49a20b2e-f469-4614-1649-08d7f90d4960") ,IsAlive = true, Name = "Kapitomas", Role = "Mafia" }); 
            _context.Players.Add(new Models.Player { PlayerId = new Guid("49a20b2e-f469-4614-1649-08d7f90d4957"), RoomId = new Guid("49a20b2e-f469-4614-1649-08d7f90d4960") ,IsAlive = true, Name = "Adomas", Role = "Civil" }); 
            _context.Players.Add(new Models.Player { PlayerId = new Guid("49a20b2e-f469-4614-1649-08d7f90d4958"), RoomId = new Guid("49a20b2e-f469-4614-1649-08d7f90d4960") ,IsAlive = true, Name = "Omas", Role = "Civil" }); 
            _context.Players.Add(new Models.Player { PlayerId = new Guid("49a20b2e-f469-4614-1649-08d7f90d4954"), RoomId = new Guid("49a20b2e-f469-4614-1649-08d7f90d4960") ,IsAlive = true, Name = "Tomas", Role = "Mafia"}); 
            _context.Players.Add(new Models.Player { PlayerId = new Guid("49a20b2e-f469-4614-1649-08d7f90d4959"), RoomId = new Guid("49a20b2e-f469-4614-1649-08d7f90d4960") ,IsAlive = true, Name = "Rudas", Role = "Civil" }); 
            _context.Players.Add(new Models.Player { PlayerId = new Guid("49a20b2e-f469-4614-1649-08d7f90d4950"), RoomId = new Guid("49a20b2e-f469-4614-1649-08d7f90d4960") ,IsAlive = true, Name = "szudas", Role = "Civil" }); 
            _context.Players.Add(new Models.Player { PlayerId = new Guid("49a20b2e-f469-4614-1649-08d7f90d4953"), RoomId = new Guid("49a20b2e-f469-4614-1649-08d7f90d4960") ,IsAlive = true, Name = "yolo", Role = "Civil" }); 
            _context.Players.Add(new Models.Player { PlayerId = new Guid("49a20b2e-f469-4614-1649-08d7f90d4952"), RoomId = new Guid("49a20b2e-f469-4614-1649-08d7f90d4960") ,IsAlive = true, Name = "trysdu", Role = "Civil" }); 
            _context.Players.Add(new Models.Player { PlayerId = new Guid("49a20b2e-f469-4614-1649-08d7f90d4951"), RoomId = new Guid("49a20b2e-f469-4614-1649-08d7f90d4960") ,IsAlive = true, Name = "keturi", Role = "Civil" });
            _context.SaveChanges();
        }
    }
}
