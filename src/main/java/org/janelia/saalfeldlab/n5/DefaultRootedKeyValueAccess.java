package org.janelia.saalfeldlab.n5;

import java.io.IOException;
import java.net.URI;

public class DefaultRootedKeyValueAccess implements RootedKeyValueAccess
{
	private final KeyValueAccess kva;
	private final URI root;

	public DefaultRootedKeyValueAccess( KeyValueAccess kva, URI root )
	{
		this.kva = kva;
		this.root = root;
	}

	@Override
	public URI root()
	{
		return root;
	}

	@Override
	public boolean exists( final String normalPath )
	{
		return false;
	}

	@Override
	public boolean isDirectory( final String normalPath )
	{
		return false;
	}

	@Override
	public boolean isFile( final String normalPath )
	{
		return false;
	}

	@Override
	public LockedChannel lockForReading( final String normalPath ) throws IOException
	{
		return null;
	}

	@Override
	public LockedChannel lockForWriting( final String normalPath ) throws IOException
	{
		return null;
	}

	@Override
	public String[] listDirectories( final String normalPath ) throws IOException
	{
		return new String[ 0 ];
	}

	@Override
	public String[] list( final String normalPath ) throws IOException
	{
		return new String[ 0 ];
	}

	@Override
	public void createDirectories( final String normalPath ) throws IOException
	{

	}

	@Override
	public void delete( final String normalPath ) throws IOException
	{

	}
}
