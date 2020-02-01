IF NOT EXISTS(
    SELECT *
    FROM sys.tables 
    WHERE Name      = 'TINY_URL') 
	BEGIN
	
		CREATE TABLE [dbo].[TINY_URL](
			[TINY_URL_ID] [numeric](18, 0) IDENTITY(1,1) NOT NULL,
			[TINY_URL] [varchar](255) NULL,
			[URL] [varchar](255) NOT NULL,
			[ENTRY_DATE] [datetime2](7) NOT NULL,
			[ENTRY_IP] [varchar](255) NOT NULL,
			[HIT_COUNT] [varchar](255) NOT NULL,
			[STATUS] [varchar](255) NULL,
			[DELETED] [char](1) NOT NULL,
		PRIMARY KEY CLUSTERED 
		(
			[TINY_URL_ID] ASC
		)WITH (IGNORE_DUP_KEY = OFF) ON [PRIMARY]
		) ON [PRIMARY]

		print 'TINY_URL Table Created Successfully'
END 

IF NOT EXISTS(
    SELECT *
    FROM sys.tables 
    WHERE Name      = 'TINY_URL_ARCHIVE') 
	BEGIN
	
		CREATE TABLE [dbo].[TINY_URL_ARCHIVE](
			[TINY_URL_ARCHIVE_ID] [numeric](18, 0) IDENTITY(1,1) NOT NULL,
			[TINY_URL] [varchar](255) NULL,
			[URL] [varchar](255) NOT NULL,
			[ENTRY_DATE] [datetime2](7) NOT NULL,
			[ENTRY_IP] [varchar](255) NOT NULL,
			[HIT_COUNT] [varchar](255) NOT NULL,
			[STATUS] [varchar](255) NULL,
			[DELETED] [char](1) NOT NULL,
		PRIMARY KEY CLUSTERED 
		(
			[TINY_URL_ARCHIVE_ID] ASC
		)WITH (IGNORE_DUP_KEY = OFF) ON [PRIMARY]
		) ON [PRIMARY]

		print 'TINY_URL Table Created Successfully'
END 